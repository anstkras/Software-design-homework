package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.text.Charsets.UTF_8

internal class UnknownCommandTest {
    @Test
    fun testUnknownCommand() {
        val unknownCommand = UnknownCommand("git")
        val byteArrayOutputStream = ByteArrayOutputStream()
        val errCode = unknownCommand.execute(InputStreamReader(System.`in`), OutputStreamWriter(byteArrayOutputStream, UTF_8))
        assert(byteArrayOutputStream.toString(UTF_8).isNotEmpty())
        assertNotEquals(0, errCode)
    }
}