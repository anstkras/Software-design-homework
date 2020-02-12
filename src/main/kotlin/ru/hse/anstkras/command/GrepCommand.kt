package ru.hse.anstkras.command

import ru.hse.anstkras.grep.Grep
import ru.hse.anstkras.grep.GrepLineSplitter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/** GrepCommand implements standard linux grep command.
 * Arguments should be in a form of "\[OPTIONS\] REGEX FILENAME?"
 * where options are:
 * -i -- makes search case-insensitive
 * -w -- matches only whole words
 * -A n -- print n lines of trailing context
 * REGEX should be a standard Java regex
 */
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

    /** Checks if this grep has a file as an input */
    fun hasFileName(): Boolean {
        return grep.hasFileName()
    }

    /** Gets fileName of an input file
     *
     * @throws IllegalStateException if this grep command does not have
     * a file as an input
     */
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