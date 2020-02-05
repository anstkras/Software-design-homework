package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * Interface that represents a command that reads from inputStream reader,
 * performs some actions and writes its output to outputStreamWriter
 * */
interface Command {
    /**
     * Performs some actions using inputStreamReader and writing output to
     * outputStreamWriter
     *
     * @throws ExitException
     * @throws IllegalStateException
     * @throws IOException
     */
    fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int
}