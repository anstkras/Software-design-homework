package ru.hse.anstkras.grep

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option

class Grep : CliktCommand() {
    // TODO validated
    private val regex by argument()
    private val file by argument().default("")
    val caseInsensitive by option("-i").flag(default = false)
    val searchByWholeWord by option("-w").flag(default = false)
    val numberOfStringToShow by option("-A").convert { it.trim().toInt() }.default(0)

    // TODO exceptions
    fun getFileName(): String {
        return file
    }

    fun hasFileName(): Boolean {
        return file.isNotEmpty()
    }

    fun getRegexString(): String {
        return regex
    }

    fun hasRegexString(): Boolean {
        return regex != null
    }

    override fun run() {
    }
}