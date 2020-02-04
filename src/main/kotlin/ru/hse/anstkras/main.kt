package ru.hse.anstkras

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.anstkras.command.CatCommand
import ru.hse.anstkras.command.Command
import ru.hse.anstkras.command.EchoCommand
import ru.hse.anstkras.commandparser.CommandParserBaseVisitor
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    try {
        while (true) {
            readLine()?.let { if (it.isNotEmpty()) runCommand(it) }
        }
    } catch (e: IllegalStateException) {
        println(e.message)
    }
}

fun runCommand(commandLine: String) {
    val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
    val parser = CommandParserParser(CommonTokenStream(lexer))
    val command = parser.line().accept(Parser())
    command.execute()
}

class Parser : CommandParserBaseVisitor<Command>() {
    override fun visitEcho_command(ctx: CommandParserParser.Echo_commandContext): Command {
        return EchoCommand(InputStreamReader(ctx.STRING().toString().byteInputStream()), OutputStreamWriter(System.out))
    }

    override fun visitCat_command(ctx: CommandParserParser.Cat_commandContext): Command {
        return CatCommand(InputStreamReader(FileInputStream(ctx.STRING().toString())), OutputStreamWriter(System.out))
    }
}