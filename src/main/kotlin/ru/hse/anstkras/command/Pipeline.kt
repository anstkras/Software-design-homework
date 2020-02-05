package ru.hse.anstkras.command

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

/**
 *  Pipeline represents several commands piped together. Commands are executed
 *  consequentially
 */
class Pipeline : Command {
    private val commands: Deque<CommandBuilder> = LinkedList<CommandBuilder>()
    private var called = false
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        if (called) {
            throw IllegalStateException("Pipeline called twice")
        }
        called = true
        if (commands.isEmpty()) {
            return 0
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

    /** Adds a command to the beginning of the commands list */
    fun addCommandFirst(commandBuilder: CommandBuilder) {
        commands.addFirst(commandBuilder)
    }

    /** Adds a command to the end of the commands list */
    fun addCommandLast(commandBuilder: CommandBuilder) {
        commands.addLast(commandBuilder)
    }
}
