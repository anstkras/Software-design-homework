package ru.hse.anstkras.command

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.InputStreamReader
import java.io.OutputStreamWriter

internal class ExitCommandTest {
    @Test
    fun testExitCommand() {
        assertThrows<ExitException> {
            ExitCommand().execute(
                InputStreamReader(System.`in`),
                OutputStreamWriter(System.out)
            )
        }
    }
}