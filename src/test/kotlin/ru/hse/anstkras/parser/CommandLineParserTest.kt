package ru.hse.anstkras.parser

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import ru.hse.anstkras.environment.Environment
import java.io.*

internal class CommandLineParserTest {
    @Test
    fun runEcho() {
        checkOutput("echo 123", "123" + System.lineSeparator())
    }

    @Test
    fun runPipeLine() {
        val file = File("testCat.txt")
        val text = "text123 !@#$%^&*()_=${System.lineSeparator()}line2"
        file.writeText(text)
        checkOutput("cat testCat.txt | wc | wc", "1 3 6" + System.lineSeparator())
        file.delete()
    }

    private fun runCommand(commandLine: String) {
        val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
        val parser = CommandParserParser(CommonTokenStream(lexer))
        val command = parser.line().accept(CommandLineParser(Environment()))
        command.build().execute()
    }

    private fun checkOutput(command: String, expected: String) {
        val out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))
        runCommand(command)
        assertEquals(expected, out.toString())
    }
}