package ru.hse.anstkras.grep

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.*

/** Class that is responsible for parsing grep's arguments.
 * After the parsing is done it provides access to the values of its options and
 * arguments
 */
class Grep : CliktCommand() {
    private val regex by argument()
    private val file by argument().default("")
    /** Specify if search is case-insensitive*/
    val caseInsensitive by option("-i").flag(default = false)
    /** Specify if only the whole words should be matched */
    val searchByWholeWord by option("-w").flag(default = false)
    /** Specify how many lines of trailing context should be printed */
    val numberOfStringToShow by option("-A").convert { it.trim().toInt() }.default(0).validate { it >= 0 }

    /** Gets fileName of an input file
     *
     * @throws IllegalStateException if this grep command does not have
     * a file as an input or parse or main methods have not been called
     */
    fun getFileName(): String {
        if (!hasFileName()) {
            throw IllegalStateException("parse or main methods have not been called or there is no file as an input")
        }
        return file
    }

    /** Checks if this grep has a file as an input */
    fun hasFileName(): Boolean {
        return file.isNotEmpty()
    }

    /** Gets regex to search
     *
     * @throws IllegalStateException if parse or main methods have not been called
     */
    fun getRegexString(): String {
        if (!hasRegexString()) {
            throw IllegalStateException("parse or main methods have not been called")
        }
        return regex
    }

    /** Checks if regex is set */
    fun hasRegexString(): Boolean {
        return regex != null
    }

    override fun run() {
    }
}