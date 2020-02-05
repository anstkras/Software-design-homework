package ru.hse.anstkras.command

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

internal class CommandBuilderTest {
    @Test
    fun testCommandBuilderCommandStrategyNotSet() {
        assertThrows<IllegalStateException> {CommandBuilder().build()}
    }

    @Test
    fun testCommandBuilderStreamsNotSet() {
        assertDoesNotThrow { CommandBuilder().commandStrategy(PwdCommand()).build() }
    }
}