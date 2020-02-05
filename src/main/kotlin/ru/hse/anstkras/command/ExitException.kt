package ru.hse.anstkras.command

import java.lang.RuntimeException

/** Exception that is used to stop CLI */
class ExitException : RuntimeException() {
}