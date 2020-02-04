import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.hse.anstkras.Parser
import ru.hse.anstkras.commandparser.CommandParserLexer
import ru.hse.anstkras.commandparser.CommandParserParser
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ParserTest {
    fun runCommand(commandLine: String) {
        val lexer = CommandParserLexer(CharStreams.fromString(commandLine))
        val parser = CommandParserParser(CommonTokenStream(lexer))
        val command = parser.line().accept(Parser())
        command.build().execute()
    }

    @Test
    fun runEcho() {
        checkOutput("echo 123", "123")
    }

    private fun checkOutput(command: String, expected: String) {
        val out = ByteArrayOutputStream()
        System.setOut(PrintStream(out))
        runCommand(command)
        assertEquals(expected, out.toString())
    }
}