package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

class WcCommand : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        var bytesNumber = 0
        var wordsNumber = 0
        var linesNumber = 0
        inputStreamReader.forEachLine {
            linesNumber++
            bytesNumber += it.toByteArray().size
            wordsNumber += it.split(" ").size
        }
        outputStreamWriter.write("$linesNumber $wordsNumber $bytesNumber ${System.lineSeparator()}")
        outputStreamWriter.flush()
        return 0
    }
}