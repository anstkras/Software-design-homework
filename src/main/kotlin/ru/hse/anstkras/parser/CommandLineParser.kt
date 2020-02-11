package ru.hse.anstkras.parser

import ru.hse.anstkras.command.*
import ru.hse.anstkras.commandparser.CommandParserBaseVisitor
import ru.hse.anstkras.commandparser.CommandParserParser
import ru.hse.anstkras.environment.Environment
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.text.Charsets.UTF_8

/** CommandLineParser is responsible for constructing commands from parse tree */
class CommandLineParser(private val environment: Environment) : CommandParserBaseVisitor<CommandBuilder>() {
    override fun visitLine(ctx: CommandParserParser.LineContext): CommandBuilder {

        if (ctx.assignment() != null) {
            return ctx.assignment().accept(this)
        } else {
            val commandBuilder = ctx.command().accept(this)
            val pipeline = Pipeline()
            if (ctx.line().isEmpty()) {
                commandBuilder.outputStreamWriter(OutputStreamWriter(System.out))
                pipeline.addCommandLast(commandBuilder)
                return CommandBuilder().commandStrategy(pipeline)
            }

            pipeline.addCommandLast(commandBuilder)

            var line = ctx.line(0)
            while (true) {
                val commandBuilder = line.command().accept(this)
                if (line.line().isEmpty()) {
                    commandBuilder.outputStreamWriter(OutputStreamWriter(System.out))
                    pipeline.addCommandLast(commandBuilder)
                    break
                } else {
                    pipeline.addCommandLast(commandBuilder)
                }
                line = line.line(0)
            }

            return CommandBuilder().commandStrategy(pipeline)
        }
    }

    override fun visitPwdCommand(ctx: CommandParserParser.PwdCommandContext?): CommandBuilder {
        return CommandBuilder()
                .commandStrategy(PwdCommand())
    }

    override fun visitEchoCommand(ctx: CommandParserParser.EchoCommandContext): CommandBuilder {
        val args = ctx.strings.joinToString(separator = " ") { it.text.trim('"', '\'') }
        return CommandBuilder()
                .commandStrategy(EchoCommand())
                .inputStreamReader(getInputStreamReaderFromString(args))
    }

    override fun visitCatCommand(ctx: CommandParserParser.CatCommandContext): CommandBuilder {
        return CommandBuilder()
                .commandStrategy(CatCommand())
                .inputStreamReader(getInputStreamReaderFromFile(ctx.getFileName()))
                .shouldCloseInputStream()
    }

    override fun visitWcCommand(ctx: CommandParserParser.WcCommandContext): CommandBuilder {
        if (ctx.STRING() == null) {
            return CommandBuilder()
                    .commandStrategy(WcCommand())
        }
        return CommandBuilder()
                .commandStrategy(WcCommand())
                .inputStreamReader(getInputStreamReaderFromFile(ctx.getFileName()))
                .shouldCloseInputStream()
    }

    override fun visitExitCommand(ctx: CommandParserParser.ExitCommandContext?): CommandBuilder {
        return CommandBuilder()
                .commandStrategy(ExitCommand())
    }

    override fun visitAssignment(ctx: CommandParserParser.AssignmentContext): CommandBuilder {
        environment.setValue(ctx.getVariable(), ctx.getValue())
        return CommandBuilder()
                .commandStrategy(Pipeline())
    }

    override fun visitUnknown(ctx: CommandParserParser.UnknownContext): CommandBuilder {
        return CommandBuilder().commandStrategy(
                UnknownCommand(
                        ctx.UNKNOWN()?.toString() ?: ctx.STRING().toString()
                )
        )
    }

    override fun visitGrep(ctx: CommandParserParser.GrepContext): CommandBuilder {
        val grepLine = ctx.GREP().toString()
        val grepCommand = GrepCommand(grepLine)
        val commandBuilder = CommandBuilder().commandStrategy(grepCommand)
        if (grepCommand.hasFileName()) {
            commandBuilder.inputStreamReader(getInputStreamReaderFromFile(grepCommand.getFileName()))
        }
        return commandBuilder
    }
}

private fun CommandParserParser.AssignmentContext.getVariable(): String {
    val variable = variable().STRING().toString()
    if (!variable.matches(Regex("[a-zA-Z0-9_]+"))) {
        throw VariableFormatException()
    }
    return variable
}

private fun CommandParserParser.AssignmentContext.getValue(): String {
    return this.value().STRING().toString().trim('"', '\'')
}

private fun CommandParserParser.CatCommandContext.getFileName(): String {
    return this.STRING().toString().trim('"', '\'')
}

private fun CommandParserParser.WcCommandContext.getFileName(): String {
    return this.STRING().toString().trim('"', '\'')
}

private fun getInputStreamReaderFromFile(fileName: String): InputStreamReader {
    return InputStreamReader(FileInputStream(fileName), UTF_8)
}

private fun getInputStreamReaderFromString(string: String): InputStreamReader {
    return InputStreamReader(string.byteInputStream(), UTF_8)
}
