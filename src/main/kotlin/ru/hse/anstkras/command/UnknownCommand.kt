package ru.hse.anstkras.command

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class UnknownCommand(private val command: String) : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        val processBuilder = ProcessBuilder()
        processBuilder.command(command.split(" "))
        val process = processBuilder.start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line = reader.readLine()
        while (line != null) {
            outputStreamWriter.write(line + System.lineSeparator())
            line = reader.readLine()
        }
        outputStreamWriter.flush()
        val exitCode = process.waitFor()
        return exitCode
    }
}