package ru.hse.anstkras.grep

class GrepLineSplitter {
    companion object {
        // TODO do smth with it
        fun getArgumentsList(arguments: String): ArrayList<String> {
            val argumentsList = ArrayList<String>()
            var currentArgument = ""
            var isInsideDoubleQuotes = false
            var isInsideSingleQuotes = false
            for (c in arguments) {
                if (c == '\'') {
                    if (isInsideSingleQuotes) {
                        isInsideSingleQuotes = false
                    } else
                        if (!isInsideDoubleQuotes) {
                            isInsideSingleQuotes = true
                        }
                } else
                    if (c == '"') {
                        if (isInsideDoubleQuotes) {
                            isInsideDoubleQuotes = false
                        } else
                            if (!isInsideSingleQuotes) {
                                isInsideDoubleQuotes = true
                            }
                    } else
                        if (c.isWhitespace() && !isInsideSingleQuotes && !isInsideDoubleQuotes) {
                            if (currentArgument.isNotEmpty()) {
                                argumentsList.add(currentArgument)
                                currentArgument = ""
                            }
                            continue
                        } else {
                            currentArgument += c
                        }
            }
            if (currentArgument.isNotEmpty()) {
                argumentsList.add(currentArgument)
            }
            if (argumentsList[0] == "grep") {
                argumentsList.removeAt(0)
            }
            return argumentsList
        }
    }
}