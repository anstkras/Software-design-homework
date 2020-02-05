package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.*
import kotlin.text.Charsets.UTF_8

internal class CatCommandTest {
    @Test
    fun testCat() {
        val catCommand = CatCommand()
        val file = File("testCat.txt")
        val text = "text123 !@#$%^&*()_=${System.lineSeparator()}line2"
        file.writeText(text)
        val byteArrayOutputStream = ByteArrayOutputStream()
        catCommand.execute(InputStreamReader(FileInputStream(file)), OutputStreamWriter(byteArrayOutputStream, UTF_8))
        file.delete()
        assertEquals(text + System.lineSeparator(), byteArrayOutputStream.toString(UTF_8))
    }
}