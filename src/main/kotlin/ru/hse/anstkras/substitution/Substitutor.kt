package ru.hse.anstkras.substitution

import ru.hse.anstkras.environment.Environment
import java.util.regex.Pattern

class Substitutor {
    companion object {
        val dollarPattern = Pattern.compile("\\$([a-zA-Z]+)")
        fun substitute(environment: Environment, string: String): String {
            val splittedString = string.split("'")
            val stringBuilder = StringBuilder()
            for ((i, substring) in splittedString.withIndex()) {
                if (i % 2 != 0) {
                    stringBuilder.append(substring)
                    continue
                }
                val matcher = dollarPattern.matcher(substring)
                stringBuilder.append(matcher.replaceAll { result -> environment.getValue(result.group(1)) })
            }
            return stringBuilder.toString()
        }
    }
}