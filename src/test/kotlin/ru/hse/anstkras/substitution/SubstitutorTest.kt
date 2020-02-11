package ru.hse.anstkras.substitution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.hse.anstkras.environment.Environment

internal class SubstitutorTest {
    @Test
    fun testNoSubstitution() {
        val text = "cat 123.txt | wc"
        assertEquals(text, Substitutor.substitute(Environment(), text))
    }

    @Test
    fun testEmptyEnvironment() {
        val text = "\$File '\$File'"
        val result = " '\$File'"
        assertEquals(result, Substitutor.substitute(Environment(), text))
    }

    @Test
    fun testSubstitution() {
        val text = "\$File '\$File' \"\$File\""
        val result = "file '\$File' \"file\""
        val environment = Environment()
        environment.setValue("File", "file")
        assertEquals(result, Substitutor.substitute(environment, text))
    }


}