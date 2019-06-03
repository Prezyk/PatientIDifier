package com.prezyk.patient_idifier.model.json

class LabResults {

    lateinit var testsGroups: List<TestGroup>
    override fun toString(): String {
        return "LabResults(testsGroups=$testsGroups)"
    }


}