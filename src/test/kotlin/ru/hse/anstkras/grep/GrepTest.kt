package ru.hse.anstkras.grep

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

internal class GrepTest {
    @Test
    fun testGrepRegex() {
        val grep = Grep()
        grep.parse(listOf("regex"))
        assertTrue(grep.hasRegexString())
        assertFalse(grep.hasFileName())
        assertThrows<IllegalStateException> { grep.getFileName() }
        assertFalse(grep.caseInsensitive)
        assertFalse(grep.searchByWholeWord)
        assertEquals(0, grep.numberOfStringToShow)
    }

    @Test
    fun testGrepWithArguments() {
        val grep = Grep()
        grep.parse(listOf("regex", "-i", "-w", "-A", "10", "fileName"))
        assertTrue(grep.hasRegexString())
        assertTrue(grep.hasFileName())
        assertEquals("regex", grep.getRegexString())
        assertEquals("fileName", grep.getFileName())
        assertTrue(grep.caseInsensitive)
        assertTrue(grep.searchByWholeWord)
        assertEquals(10, grep.numberOfStringToShow)
    }
}