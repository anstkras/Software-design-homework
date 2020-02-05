package ru.hse.anstkras.command

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.IllegalStateException
import java.util.*

class Pipeline : Command {
    private val commands: Deque<CommandBuilder> = LinkedList<CommandBuilder>()
    private var called = false
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        if (called) {
            throw IllegalStateException("Pipeline called twice")
        }
        called = true
        if (commands.isEmpty()) {
            return 0;
        }
        if (!commands.first.isSetInputStreamReader()) {
            commands.first.inputStreamReader(InputStreamReader(System.`in`))
        }
        var byteArrayOutputStream = ByteArrayOutputStream()
        var returnCode = 0
        commands.forEachIndexed { index, it ->
            if (!it.isSetInputStreamReader()) {
                it.inputStreamReader(InputStreamReader(ByteArrayInputStream(byteArrayOutputStream.toByteArray())))
            }
            byteArrayOutputStream = ByteArrayOutputStream()
            if (index != commands.size - 1) {
                it.outputStreamWriter(OutputStreamWriter(byteArrayOutputStream))
            }
            returnCode = it.build().execute()
        }
        return returnCode
    }

    fun addCommandFirst(commandBuilder: CommandBuilder) {
        commands.addFirst(commandBuilder)
    }

    fun addCommandLast(commandBuilder: CommandBuilder) {
        commands.addLast(commandBuilder)
    }
}
