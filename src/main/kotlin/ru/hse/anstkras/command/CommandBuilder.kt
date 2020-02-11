package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 *  Class for building a command that is used when it is not possible to
 * know all the details at once
 */
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

    /** Defines what command will be executed */
    fun commandStrategy(commandStrategy: Command) = apply {
        this.commandStrategy = commandStrategy
    }

    /** Defines what inputStreamReader to use */
    fun inputStreamReader(inputStreamReader: InputStreamReader) = apply {
        this.inputStreamReader = inputStreamReader
    }

    /** Defines what outputStreamWriter to use */
    fun outputStreamWriter(outputStreamWriter: OutputStreamWriter) = apply {
        this.outputStreamWriter = outputStreamWriter
    }

    /** Defines if inputStream should be closed after command execution */
    fun shouldCloseInputStream(shouldCloseInputStream: Boolean = true) = apply {
        this.shouldCloseInputStream = shouldCloseInputStream
    }

    /** Defines if outputStream should be closed after command execution */
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