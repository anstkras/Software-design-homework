package ru.hse.anstkras.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.text.Charsets.UTF_8

internal class PipelineTest {
    @Test
    fun testEmptyPipeLine() {
        val pipeline = Pipeline()
        assertEquals(0, pipeline.execute(InputStreamReader(System.`in`), OutputStreamWriter(System.out)))
    }

    @Test
    fun testExecutePipelineTwice() {
        val pipeline = Pipeline()
        pipeline.addCommandLast(
            CommandBuilder().commandStrategy(PwdCommand()).outputStreamWriter(
                OutputStreamWriter(
                    ByteArrayOutputStream()
                )
            )
        )
        pipeline.execute(InputStreamReader(System.`in`), OutputStreamWriter(System.out))
        assertThrows<IllegalStateException> { pipeline.execute(InputStreamReader(System.`in`), OutputStreamWriter(System.out)) }
    }

    @Test
    fun testExecutePipeLine() {
        val pipeline = Pipeline()
        val byteArrayOutputStream = ByteArrayOutputStream()
        pipeline.addCommandLast(
            CommandBuilder().commandStrategy(EchoCommand())
                .inputStreamReader(InputStreamReader("123".byteInputStream(UTF_8)))
        )
        pipeline.addCommandLast(
            CommandBuilder().commandStrategy(WcCommand())
                .outputStreamWriter(OutputStreamWriter(byteArrayOutputStream, UTF_8))
        )
        pipeline.execute(InputStreamReader(System.`in`), OutputStreamWriter(System.out))
        assertEquals("1 1 3" + System.lineSeparator(), byteArrayOutputStream.toString(UTF_8))
    }
}