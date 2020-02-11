package ru.hse.anstkras.grep

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.options.*

class Grep : CliktCommand() {
    private val regex by argument()
    private val file by argument().default("")
    val caseInsensitive by option("-i").flag(default = false)
    val searchByWholeWord by option("-w").flag(default = false)
    val numberOfStringToShow by option("-A").convert { it.trim().toInt() }.default(0).validate { it >= 0 }

    fun getFileName(): String {
        if (!hasFileName()) {
            throw IllegalStateException("parse or main methods have not been called")
        }
        return file
    }

    fun hasFileName(): Boolean {
        return file.isNotEmpty()
    }

    fun getRegexString(): String {
        if (!hasRegexString()) {
            throw IllegalStateException("parse or main methods have not been called")
        }
        return regex
    }

    fun hasRegexString(): Boolean {
        return regex != null
    }

    override fun run() {
    }
}