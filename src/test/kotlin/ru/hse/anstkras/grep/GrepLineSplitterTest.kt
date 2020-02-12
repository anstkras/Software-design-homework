package ru.hse.anstkras.grep

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GrepLineSplitterTest {
    @Test
    fun testWithoutArgs() {
        val argumentsList = GrepLineSplitter.getArgumentsList("grep regex fileName")
        val expectedList = listOf("regex", "fileName")
        assertEquals(expectedList, argumentsList)
    }

    @Test
    fun testWithArguments() {
        val argumentsList = GrepLineSplitter.getArgumentsList("grep -i -w -A  10 regex fileName")
        val expectedList = listOf("-i", "-w", "-A", "10", "regex", "fileName")
        assertEquals(expectedList, argumentsList)
    }

    @Test
    fun testSingleQuotes() {
        val argumentsList = GrepLineSplitter.getArgumentsList("grep -i -w -A  10 'a \"s\" dfs qwe' fileName")
        val expectedList = listOf("-i", "-w", "-A", "10", "a \"s\" dfs qwe", "fileName")
        assertEquals(expectedList, argumentsList)
    }

    @Test
    fun testDoubleQuotes() {
        val argumentsList = GrepLineSplitter.getArgumentsList("grep -i -w -A  10 \"a 's' dfs qwe\" fileName")
        val expectedList = listOf("-i", "-w", "-A", "10", "a 's' dfs qwe", "fileName")
        assertEquals(expectedList, argumentsList)
    }
}