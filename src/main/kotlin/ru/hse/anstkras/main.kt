package ru.hse.anstkras

import ru.hse.anstkras.commandlineinterpretator.CommandLineInterpreter

/** Calls commandLineInterpreter */
fun main(args: Array<String>) {
    val commandLineInterpreter = CommandLineInterpreter()
    commandLineInterpreter.run()
}
