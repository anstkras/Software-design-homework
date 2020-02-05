package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.text.Charsets.UTF_8

internal class EchoCommandTest {
    @Test
    fun testEchoCommand() {
        val echoCommand = EchoCommand()
        val text = "text123 !@#$%^&*()_=${System.lineSeparator()}line2"
        val byteArrayOutputStream = ByteArrayOutputStream()
        echoCommand.execute(
            InputStreamReader(text.byteInputStream(UTF_8)),
            OutputStreamWriter(byteArrayOutputStream, UTF_8)
        )
        assertEquals(text + System.lineSeparator(), byteArrayOutputStream.toString(UTF_8))
    }

    fun testEchoCat() {
        val echoCommand = EchoCommand()
        val text = "cat"
        val byteArrayOutputStream = ByteArrayOutputStream()
        echoCommand.execute(
            InputStreamReader(text.byteInputStream(UTF_8)),
            OutputStreamWriter(byteArrayOutputStream, UTF_8)
        )
        assertEquals("cat" + System.lineSeparator(), byteArrayOutputStream.toString(UTF_8))
    }
}