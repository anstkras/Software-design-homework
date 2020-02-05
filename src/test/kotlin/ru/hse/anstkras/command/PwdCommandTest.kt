package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Paths
import kotlin.text.Charsets.UTF_8

internal class PwdCommandTest {
    @Test
    fun testPwd() {
        val pwdCommand = PwdCommand()
        val byteArrayOutputStream = ByteArrayOutputStream()
        pwdCommand.execute(InputStreamReader(System.`in`), OutputStreamWriter(byteArrayOutputStream, UTF_8))
        assertEquals(
            Paths.get("").toAbsolutePath().toString() + System.lineSeparator(),
            byteArrayOutputStream.toString(UTF_8)
        )
    }
}