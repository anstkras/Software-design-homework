package ru.hse.anstkras.command

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class UnknownCommand(private val command : String) : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        val processBuilder = ProcessBuilder()
        processBuilder.command(command)

        try {
            val process = processBuilder.start()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                outputStreamWriter.write(line + System.lineSeparator())
            }
            val exitCode = process.waitFor()
            //println("\nExited with error code : $exitCode")
        } catch (e: IOException) {
            //e.printStackTrace()
        } catch (e: InterruptedException) {
            //e.printStackTrace()
        }
        return 0
    }
}