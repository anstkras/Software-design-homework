package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.*

internal class WcCommandTest {
    @Test
    fun testWcCommand() {
        val wcCommand = WcCommand()
        val file = File("testWc.txt")
        val text = "text123 !@#$%^&*()_=${System.lineSeparator()}line2"
        file.writeText(text)
        val byteArrayOutputStream = ByteArrayOutputStream()
        wcCommand.execute(InputStreamReader(FileInputStream(file)), OutputStreamWriter(byteArrayOutputStream, Charsets.UTF_8))
        file.delete()
        assertEquals("2 3 25" + System.lineSeparator(), byteArrayOutputStream.toString(Charsets.UTF_8))
    }
}