package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

/** Command that stops CLI */
class ExitCommand : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        throw ExitException()
    }
}