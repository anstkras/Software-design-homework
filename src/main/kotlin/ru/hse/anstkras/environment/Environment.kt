package ru.hse.anstkras.environment

/** Environment holds environment state, for example, environment variables */
class Environment {
    private val environmentMap = HashMap<String, String>()

    init {
        environmentMap["?"] = "0"
    }

    /** Sets the given value for the given variable */
    fun setValue(variable : String, value : String) {
        environmentMap[variable] = value
    }

    /** Gets the value for the given variable */
    fun getValue(variable : String) : String {
        return environmentMap.getOrDefault(variable, "")
    }
}
