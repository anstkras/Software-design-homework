package ru.hse.anstkras.command

import ru.hse.anstkras.grep.Grep
import ru.hse.anstkras.grep.GrepLineSplitter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class GrepCommand : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        val grepLine = inputStreamReader.readText()
        Grep().main((GrepLineSplitter.getArgumentsList(grepLine)))
        return 0
    }
}