package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

internal class PipelineCommandTest {
    @Test
    fun testPipeLineCommand() {
        val echoCommand = EchoCommand()
        val text = "text123 !@#$%^&*()_=${System.lineSeparator()}line2"
        val byteArrayOutputStream = ByteArrayOutputStream()
        val pipelineCommand = PipelineCommand(
            echoCommand,
            InputStreamReader(text.byteInputStream(Charsets.UTF_8)),
            OutputStreamWriter(byteArrayOutputStream, Charsets.UTF_8)
            , false, false
        )
        pipelineCommand.execute()
        assertEquals(text + System.lineSeparator(), byteArrayOutputStream.toString(Charsets.UTF_8))
    }
}