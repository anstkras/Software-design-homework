package ru.hse.anstkras.command

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

//comment that here could be pipeline or command
class Pipeline : Command {
    private val commands: Deque<PipelineCommand> = LinkedList()
    // TODO check called once
    override fun execute(inputStreamReader: InputStreamReader, outputStreamWriter: OutputStreamWriter): Int {
        if (commands.isEmpty()) {
            return 0;
        }
        if (!commands.first.isSetInputStreamReader()) {
            commands.first.inputStreamReader = InputStreamReader(System.`in`)
        }
        var byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
        commands.forEachIndexed { index, it ->
            if (!it.isSetInputStreamReader()) {
                it.inputStreamReader = InputStreamReader(ByteArrayInputStream(byteArrayOutputStream.toByteArray()))
            }
            byteArrayOutputStream = ByteArrayOutputStream()
            if (index != commands.size - 1) {
                it.outputStreamWriter = OutputStreamWriter(byteArrayOutputStream)
            }
            it.execute(it.inputStreamReader!!, it.outputStreamWriter!!)
        }
        return 0
    }

    fun addCommandFirst(command: PipelineCommand) {
        commands.addFirst(command)
    }

    fun addCommandLast(command: PipelineCommand) {
        commands.addLast(command)
    }
}
