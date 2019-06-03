package com.prezyk.patient_idifier.model.json

class Result {

    var value: Double = 0.0
    lateinit var unit: String
    var lowerLimit: Double = 0.0
    var upperLimit: Double = 0.0
    lateinit var name: String
    override fun toString(): String {
        return "Result(value=$value, unit=$unit, lowerLimit=$lowerLimit, upperLimit=$upperLimit, name=$name)"
    }


}