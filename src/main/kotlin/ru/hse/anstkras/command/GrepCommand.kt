package ru.hse.anstkras.command

import ru.hse.anstkras.grep.Grep
import ru.hse.anstkras.grep.GrepLineSplitter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.IllegalStateException

class GrepCommand(arguments: String) : Command {
    private val grep = Grep()

    init {
        try {
            val splittedArguments = GrepLineSplitter.getArgumentsList(arguments)
            grep.parse(splittedArguments)
        } catch (e: Exception) {
            throw IllegalStateException("Wrong parameters")
        }
    }

    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        val regex = getRegex()
        var linesToPrint = 0
        inputStreamReader.forEachLine {
            if (it.contains(regex)) {
                outputStreamWriter.write(it + System.lineSeparator())
                linesToPrint = grep.numberOfStringToShow
            } else
                if (linesToPrint > 0) {
                    outputStreamWriter.write(it + System.lineSeparator())
                    linesToPrint--
                }
        }
        outputStreamWriter.flush()
        return 0
    }

    fun hasFileName(): Boolean {
        return grep.hasFileName()
    }

    fun getFileName(): String {
        return grep.getFileName()
    }

    private fun getRegex(): Regex {
        var regexString = grep.getRegexString()
        if (grep.searchByWholeWord) {
            regexString = "\\b$regexString\\b"
        }
        if (grep.caseInsensitive) {
            return Regex(regexString, RegexOption.IGNORE_CASE)
        }
        return Regex(regexString)
    }
}