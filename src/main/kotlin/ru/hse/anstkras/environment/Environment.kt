package ru.hse.anstkras.environment

class Environment {
    private val environmentMap = HashMap<String, String>()

    init {
        environmentMap["?"] = "0"
    }

    fun setValue(variable : String, value : String) {
        environmentMap[variable] = value
    }

    fun getValue(variable : String) : String {
        return environmentMap.getOrDefault(variable, "")
    }
}
