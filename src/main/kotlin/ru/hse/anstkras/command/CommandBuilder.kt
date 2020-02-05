package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class CommandBuilder {
    var commandStrategy: Command? = null
        private set
    var inputStreamReader: InputStreamReader? = null
        private set
    var outputStreamWriter: OutputStreamWriter? = null
        private set

    var shouldCloseInputStream: Boolean = false
        private set

    var shouldCloseOutputStream: Boolean = false
        private set

    fun commandStrategy(commandStrategy: Command) = apply {
        this.commandStrategy = commandStrategy
    }

    fun inputStreamReader(inputStreamReader: InputStreamReader) = apply {
        this.inputStreamReader = inputStreamReader
    }

    fun outputStreamWriter(outputStreamWriter: OutputStreamWriter) = apply {
        this.outputStreamWriter = outputStreamWriter
    }

    fun shouldCloseInputStream(shouldCloseInputStream: Boolean = true) = apply {
        this.shouldCloseInputStream = shouldCloseInputStream
    }

    fun shouldCloseOutputStream(shouldCloseOutputStream: Boolean = true) = apply {
        this.shouldCloseOutputStream = shouldCloseOutputStream
    }

    fun isSetCommandStrategy() = commandStrategy != null
    fun isSetInputStreamReader() = inputStreamReader != null
    fun isSetOutputStreamWriter() = outputStreamWriter != null

    fun build(): PipelineCommand {
        if (commandStrategy == null) {
            throw IllegalStateException("Сommand strategy must be set")
        }
        if (inputStreamReader == null) {
            inputStreamReader = InputStreamReader(System.`in`)
        }
        if (outputStreamWriter == null) {
            outputStreamWriter = OutputStreamWriter(System.out)
        }
        return PipelineCommand(
            commandStrategy!!,
            inputStreamReader!!,
            outputStreamWriter!!,
            shouldCloseInputStream,
            shouldCloseOutputStream
        )
    }
}