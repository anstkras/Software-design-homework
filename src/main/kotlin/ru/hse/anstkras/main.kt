package ru.hse.anstkras

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.anstkras.parser.CommandLineParser
import ru.hse.anstkras.command.ExitException
import ru.hse.anstkras.substitution.Substitutor
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import ru.hse.anstkras.environment.Environment


fun main(args: Array<String>) {
    val environment = Environment()
    try {
        while (true) {
            readLine()?.let { if (it.isNotEmpty()) runCommand(Substitutor.substitute(environment, it), environment) }
        }
    } catch (e: IllegalStateException) {
        println(e.message)
    } catch (e: ExitException) {

    }
}

fun runCommand(commandLine: String, environment: Environment) {
    val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
    val parser = CommandParserParser(CommonTokenStream(lexer))
    val command = parser.line().accept(CommandLineParser(environment))
    command.build().execute()
}