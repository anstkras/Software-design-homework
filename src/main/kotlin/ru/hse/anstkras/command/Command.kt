package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

interface Command {
    // TODO ?
    fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int
}