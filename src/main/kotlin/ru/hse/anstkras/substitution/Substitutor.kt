package ru.hse.anstkras.substitution

import ru.hse.anstkras.environment.Environment
import java.util.regex.Pattern

/**
 * Substitutor is responsible for substitution of environment variables
 * using correct rules for single and double quotes
 */
class Substitutor {
    companion object {
        private val dollarPattern = Pattern.compile("\\$([a-zA-Z\\?]+)")

        /** Substitutes environment variables using correct rules for single and double quotes **/
        fun substitute(environment: Environment, string: String): String {
            val splittedString = string.split("'")
            val stringBuilder = StringBuilder()
            for ((i, substring) in splittedString.withIndex()) {
                if (i % 2 != 0) {
                    stringBuilder.append(substring)
                    if (i != splittedString.lastIndex) {
                        stringBuilder.append('\'')
                    }
                    continue
                }
                val matcher = dollarPattern.matcher(substring)
                stringBuilder.append(matcher.replaceAll { result -> environment.getValue(result.group(1)) })
                if (i != splittedString.lastIndex) {
                    stringBuilder.append('\'')
                }
            }
            return stringBuilder.toString()
        }
    }
}