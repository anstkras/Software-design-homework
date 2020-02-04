package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class EchoCommand(private val reader: InputStreamReader, private val writer: OutputStreamWriter) : PipelineCommand {
    override fun execute(): Int {
        writer.write(reader.readText() + System.lineSeparator())
        writer.flush()
        return 0
    }
}