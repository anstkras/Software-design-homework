package ru.hse.anstkras.command

import java.util.regex.Pattern

class Substitutor {
    companion object {
        val dollarPattern = Pattern.compile("\\$([a-zA-Z]+)")
        fun substitute(environment: HashMap<String, String>, string: String): String {
            val splittedString = string.split("'")
            val stringBuilder = StringBuilder()
            for ((i, substring) in splittedString.withIndex()) {
                if (i % 2 != 0) {
                    stringBuilder.append(substring)
                    continue
                }
                val matcher = dollarPattern.matcher(substring)
                stringBuilder.append(matcher.replaceAll { result -> environment.getOrDefault(result.group(1), "") })
            }
            return stringBuilder.toString()
        }
    }
}