package com.prezyk.patient_idifier.model.json

class TestGroup {

    lateinit var results: List<Result>

    lateinit var name: String
    override fun toString(): String {
        return "TestGroup(results=$results, name=$name)"
    }


}