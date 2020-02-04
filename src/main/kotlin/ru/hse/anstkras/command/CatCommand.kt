package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class CatCommand(private val reader: InputStreamReader, private val writer: OutputStreamWriter) : PipelineCommand {
    override fun execute(): Int {
        reader.forEachLine {
            writer.write(it + System.lineSeparator())
        }
        writer.flush()
        return 0
    }
}