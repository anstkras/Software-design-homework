package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PipelineCommand(
    private val command: Command, private val inputStreamReader: InputStreamReader,
    private val outputStreamWriter: OutputStreamWriter
) {
    fun execute(): Int {
        return command.execute(inputStreamReader, outputStreamWriter)
    }
}