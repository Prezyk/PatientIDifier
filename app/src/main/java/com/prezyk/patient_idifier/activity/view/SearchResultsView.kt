package com.prezyk.patient_idifier.activity.view

import java.util.*

interface SearchResultsView {

    fun enableTextViewStartDate()
    fun disableTextViewStartDate()
    fun enableTextViewEndDate()
    fun disableTextViewEndDate()
    fun enableSpinnerTestType()
    fun disableSpinnerTestType()
    fun showStartDatePicker(year: Int, month: Int, day: Int)
    fun showEndDatePicker(year: Int, month: Int, day: Int)
    fun navToResultsSearchResults(patientID: Long, startDate: Calendar?, endDate: Calendar?, testType: String?)
    fun updateTextViewStartDate(text: String)
    fun updateTextViewEndDate(text: String)



}