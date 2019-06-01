package com.prezyk.patient_idifier.model

import java.io.Serializable
import java.util.*

class Result(var id: Long, var testType: String, var date: Date, var description: String) : Serializable{

}