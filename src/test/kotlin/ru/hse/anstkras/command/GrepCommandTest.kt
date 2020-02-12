package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.*

internal class GrepCommandTest {
    @Test
    fun testWithFile() {
        val grepCommand = GrepCommand("-i -A 1 qwe testGrep.txt")
        val file = File("testGrep.txt")
        val expectedText = "line1_qwe${System.lineSeparator()}line2_qwe_line2${System.lineSeparator()}line3${System.lineSeparator()}"
        val fileText = expectedText + "line4"
        file.writeText(fileText)
        val byteArrayOutputStream = ByteArrayOutputStream()
        grepCommand.execute(InputStreamReader(FileInputStream(file)), OutputStreamWriter(byteArrayOutputStream, Charsets.UTF_8))
        file.delete()
        assertEquals(expectedText, byteArrayOutputStream.toString(Charsets.UTF_8))
    }

    @Test
    fun testWrongArgs() {
        assertThrows<IllegalStateException> { GrepCommand("-t -A -1 qwe testGrep.txt") }
    }
}