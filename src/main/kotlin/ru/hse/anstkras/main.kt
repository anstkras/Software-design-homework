package ru.hse.anstkras

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.anstkras.command.*
import ru.hse.anstkras.commandparser.CommandParserBaseVisitor
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main(args: Array<String>) {
    try {
        while (true) {
            readLine()?.let { if (it.isNotEmpty()) runCommand(Substitutor.substitute(environmentMap, it)) }
        }
    } catch (e: IllegalStateException) {
        println(e.message)
    } catch (e: ExitException) {

    }
}

fun runCommand(commandLine: String) {
    val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
    val parser = CommandParserParser(CommonTokenStream(lexer))
    val command = parser.line().accept(Parser())
    command.build().execute()
}

val environmentMap = HashMap<String, String>()

class Parser : CommandParserBaseVisitor<CommandBuilder>() {
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
        return CommandBuilder().commandStrategy(PwdCommand())
    }

    override fun visitEcho_command(ctx: CommandParserParser.Echo_commandContext): CommandBuilder {
        return CommandBuilder().commandStrategy(EchoCommand())
            .inputStreamReader(InputStreamReader(ctx.STRING().toString().byteInputStream()))
    }

    override fun visitCat_command(ctx: CommandParserParser.Cat_commandContext): CommandBuilder {
        return CommandBuilder().commandStrategy(CatCommand())
            .inputStreamReader(InputStreamReader(FileInputStream(ctx.STRING().toString())))
    }

    override fun visitWcCommand(ctx: CommandParserParser.WcCommandContext): CommandBuilder {
        if (ctx.STRING() == null) {
            return CommandBuilder().commandStrategy(WcCommand())
        }
        return CommandBuilder().commandStrategy(WcCommand())
            .inputStreamReader(InputStreamReader(FileInputStream(ctx.STRING().toString())))
    }

    override fun visitExitCommand(ctx: CommandParserParser.ExitCommandContext?): CommandBuilder {
        return CommandBuilder().commandStrategy(ExitCommand())
    }

    override fun visitAssignment(ctx: CommandParserParser.AssignmentContext): CommandBuilder {
//       TODO check variable
        environmentMap[ctx.variable().STRING().toString()] = ctx.value().STRING().toString()
        return CommandBuilder().commandStrategy(Pipeline())
    }

    override fun visitUnknown(ctx: CommandParserParser.UnknownContext): CommandBuilder {
        return CommandBuilder().commandStrategy(UnknownCommand(ctx.UNKNOWN()?.toString() ?: ctx.STRING().toString()))
    }
}