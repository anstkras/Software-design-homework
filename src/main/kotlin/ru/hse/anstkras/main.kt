package ru.hse.anstkras

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.anstkras.command.*
import ru.hse.anstkras.commandparser.CommandParserBaseVisitor
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import java.io.*

fun main(args: Array<String>) {
    try {
        while (true) {
            readLine()?.let { if (it.isNotEmpty()) runCommand(it) }
            break
        }
    } catch (e: IllegalStateException) {
        println(e.message)
    }
}

fun runCommand(commandLine: String) {
    val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
    val parser = CommandParserParser(CommonTokenStream(lexer))
    val command = parser.line().accept(Parser())
    command.build().execute()
}

class Parser : CommandParserBaseVisitor<CommandBuilder>() {
    override fun visitLine(ctx: CommandParserParser.LineContext): CommandBuilder {

        val commandBuilder = ctx.command().accept(this)
        val pipeline = Pipeline()
        if (ctx.line().isEmpty()) {
            commandBuilder.outputStreamWriter(OutputStreamWriter(System.out))
            pipeline.addCommandLast(commandBuilder)
            return CommandBuilder().commandStrategy(pipeline)
        }

        pipeline.addCommandLast(commandBuilder)

        ctx.line().forEachIndexed { index, it ->
            val commandBuilder = it.command().accept(this)
            if (index == ctx.line().lastIndex) {
                commandBuilder.outputStreamWriter(OutputStreamWriter(System.out))
            }
            pipeline.addCommandLast(commandBuilder)
        }

        return CommandBuilder().commandStrategy(pipeline)
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
}