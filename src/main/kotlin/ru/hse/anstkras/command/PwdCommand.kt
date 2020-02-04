package ru.hse.anstkras.command

import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.nio.file.Paths

class PwdCommand : Command {
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        val currentPath = Paths.get("")
        val pwd = currentPath.toAbsolutePath().toString()
        outputStreamWriter.write(pwd + System.lineSeparator())
        outputStreamWriter.flush()
        return 0
    }
}