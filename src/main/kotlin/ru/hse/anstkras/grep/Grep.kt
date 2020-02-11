package ru.hse.anstkras.grep

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.convert
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option

class Grep : CliktCommand() {
    // TODO validated
    private val regex by argument()
    private val file by argument()
    private val caseInsensitive by option("-i").flag(default = false)
    private val searchByWholeWord by option("-w").flag(default = false)
    private val numberOfStringToShow by option("-A").convert { it.trim().toInt() }.default(1)

    override fun run() {
        echo (regex)
        echo(file)
        echo(caseInsensitive)
        echo(searchByWholeWord)
        echo(numberOfStringToShow)
    }
}