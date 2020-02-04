package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class EchoCommand : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        outputStreamWriter.write(inputStreamReader.readText() + System.lineSeparator())
        outputStreamWriter.flush()
        return 0
    }
}