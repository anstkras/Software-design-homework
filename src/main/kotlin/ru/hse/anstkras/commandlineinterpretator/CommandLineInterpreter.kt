package ru.hse.anstkras.commandlineinterpretator

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import ru.hse.anstkras.command.ExitException
import ru.hse.anstkras.command.VariableFormatException
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import ru.hse.anstkras.environment.Environment
import ru.hse.anstkras.parser.CommandLineParser
import ru.hse.anstkras.substitution.Substitutor
import java.io.IOException

/** CommandLineInterpreter is responsible for interaction with user */
class CommandLineInterpreter {
    private val environment = Environment()

    /** Starts commandLineInterpreter */
    fun run() {
        try {
            while (true) {
                readLine()?.let {
                    if (it.isNotEmpty()) runCommand(
                        Substitutor.substitute(environment, it),
                        environment
                    )
                }
            }
        } catch (e: IllegalStateException) {
            environment.setValue("?", "1")
        } catch (e: VariableFormatException) {
            environment.setValue("?", "127")
        } catch (e: IOException) {
            environment.setValue("?", "1")
        } catch (e: ExitException) {
        }
    }

    private fun runCommand(commandLine: String, environment: Environment) {
        val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
        val parser = CommandParserParser(CommonTokenStream(lexer))
        val command = parser.line().accept(CommandLineParser(environment))
        environment.setValue("?", command.build().execute().toString())
    }
}