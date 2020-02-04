package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class ExitCommand : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        throw ExitException()
    }
}