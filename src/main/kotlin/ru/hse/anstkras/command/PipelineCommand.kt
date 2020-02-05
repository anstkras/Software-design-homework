package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class PipelineCommand(
    private val command: Command, private val inputStreamReader: InputStreamReader,
    private val outputStreamWriter: OutputStreamWriter,
    private val shouldCloseInputStream: Boolean,
    private val shouldCloseOutputStream: Boolean
) {
    fun execute(): Int {
        val returnCode = command.execute(inputStreamReader, outputStreamWriter)

        if (shouldCloseInputStream) {
            inputStreamReader.close()
        }
        if (shouldCloseOutputStream) {
            outputStreamWriter.close()
        }

        return returnCode
    }
}