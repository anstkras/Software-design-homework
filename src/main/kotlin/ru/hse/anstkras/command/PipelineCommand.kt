package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PipelineCommand(
    val command: Command, var inputStreamReader: InputStreamReader?,
    var outputStreamWriter: OutputStreamWriter?
) {
    fun isSetInputStreamReader() = inputStreamReader != null
    fun isSetOutputStreamWriter() = outputStreamWriter != null

    fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        return command.execute(inputStreamReader, outputStreamWriter)
    }
}