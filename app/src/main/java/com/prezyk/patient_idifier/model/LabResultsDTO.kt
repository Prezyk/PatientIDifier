package com.prezyk.patient_idifier.model

import com.prezyk.patient_idifier.model.json.LabResults
import com.prezyk.patient_idifier.model.json.Result
import com.prezyk.patient_idifier.model.json.TestGroup

class LabResultsDTO(var results: LabResults) {

    lateinit var isTestGroupName: ArrayList<Boolean>
    lateinit var data: ArrayList<Array<String>>

    init {

        isTestGroupName = ArrayList<Boolean>()
        data = ArrayList<Array<String>>()

        for(tg: TestGroup in results.testsGroups!!) {
            isTestGroupName.add(true)
            data.add(arrayOf(tg.name))
            for(r: Result in tg.results) {
                isTestGroupName.add(false)
                data.add(arrayOf(r.value.toString(), r.unit, r.lowerLimit.toString(), r.upperLimit.toString(), r.name))
            }
        }


    }



//    fun getData() : List<String> {
//
//
//
//
//    }

}