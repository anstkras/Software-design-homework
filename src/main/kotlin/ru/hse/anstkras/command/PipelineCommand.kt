package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PipelineCommand(
    val command: Command, var inputStreamReader: InputStreamReader,
    var outputStreamWriter: OutputStreamWriter
) {
    fun execute(): Int {
        return command.execute(inputStreamReader, outputStreamWriter)
    }
}