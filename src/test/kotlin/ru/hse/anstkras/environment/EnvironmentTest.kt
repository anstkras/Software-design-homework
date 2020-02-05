package ru.hse.anstkras.environment

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EnvironmentTest {
    @Test
    fun testGetFromEmptyEnvironment() {
        val environment = Environment()
        assertEquals("", environment.getValue("abc"))
    }

    @Test
    fun testSetAndGet() {
        val environment = Environment()
        environment.setValue("a", "b")
        assertEquals("b", environment.getValue("a"))
    }

    @Test
    fun testSetTwiceAndGet() {
        val environment = Environment()
        environment.setValue("a", "b")
        environment.setValue("a", "c")
        assertEquals("c", environment.getValue("a"))
    }
}