package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter

interface Command {
    fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int
}