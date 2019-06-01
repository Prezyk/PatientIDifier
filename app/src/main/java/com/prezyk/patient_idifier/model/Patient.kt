package com.prezyk.patient_idifier.model

import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class Patient {
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var pesel: String? = null
    var gender: String? = null
    var birthdate: Date? = null
    var phoneNumber: String? = null
    var allergies: List<String>? = null

    constructor(
        patientID: Long,
        firstName: String,
        lastName: String,
        PESEL: String,
        gender: String,
        birthdate: Date,
        phoneNumber: String,
        allergies: List<String>
    ) {
        this.id = patientID
        this.firstName = firstName
        this.lastName = lastName
        this.pesel = PESEL
        this.gender = gender
        this.birthdate = birthdate
        this.phoneNumber = phoneNumber
        this.allergies = allergies
    }


    fun getData() : Array<String?> {
        var array = arrayOfNulls<String>(6)
        array[0] = firstName
        array[1] = lastName
        array[2] = gender
        var simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        array[3] = simpleDateFormat.format(birthdate)
        array[4] = phoneNumber
        var sb = StringBuilder()

        if(this.allergies!=null && this.allergies!!.isNotEmpty()) {
            for (s: String in allergies!!) {
                sb.append(s + "\n")
            }
            sb.deleteCharAt(sb.length - 1)
        } else {
            sb.append("")
        }
        array[5] = sb.toString()
        return array
    }
}