package com.prezyk.patient_idifier.presenter

import com.prezyk.patient_idifier.activity.view.SearchResultsView
import java.text.SimpleDateFormat
import java.util.*

class SearchResultsPresenter(var view: SearchResultsView) {

    var patientID: Long = 0L
    var startDate: Calendar? = null
    var endDate: Calendar? = null
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    var testType: String? = null
    var checkedOptions = arrayOf(false, false, false)


    init {
        this.startDate = Calendar.getInstance()
        this.startDate?.clear(Calendar.HOUR_OF_DAY)
        this.startDate?.clear(Calendar.MINUTE)
        this.startDate?.clear(Calendar.SECOND)
        this.startDate?.clear(Calendar.MILLISECOND)
        this.endDate = this.startDate?.clone() as Calendar

        view.updateTextViewStartDate(dateFormat.format(startDate?.time) ?: dateFormat.toPattern())
        view.updateTextViewEndDate(dateFormat.format(startDate?.time)?: dateFormat.toPattern())
    }

    fun setStartDateOption(value: Boolean) {
        this.checkedOptions[0] = value
    }

    fun setEndDateOption(value: Boolean) {
        this.checkedOptions[1] = value
    }

    fun setTestTypeOption(value: Boolean) {
        this.checkedOptions[2] = value
    }


    fun setStartDate(day: Int, month: Int, year: Int) {
        startDate?.set(year, month, day)
        view.updateTextViewStartDate(dateFormat.format(startDate?.time) ?: dateFormat.toPattern())
    }

    fun setEndDate(day: Int, month: Int, year: Int) {
        endDate?.set(year, month, day)
        view.updateTextViewEndDate(dateFormat.format(endDate?.time) ?: dateFormat.toPattern())
    }

    fun startDateTextViewClicked() {
        view.showStartDatePicker(startDate?.get(Calendar.YEAR) ?:  Calendar.getInstance().get(Calendar.YEAR),
        startDate?.get(Calendar.MONTH) ?: Calendar.getInstance().get(Calendar.MONTH),
        startDate?.get(Calendar.DAY_OF_MONTH)?: Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    }

    fun endDateTextViewClicked() {
        view.showEndDatePicker(endDate?.get(Calendar.YEAR) ?:  Calendar.getInstance().get(Calendar.YEAR),
            endDate?.get(Calendar.MONTH) ?: Calendar.getInstance().get(Calendar.MONTH),
            endDate?.get(Calendar.DAY_OF_MONTH) ?: Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    }

    fun onSearchButtonClicked() {

        startDate = if(checkedOptions[0] ) startDate else null
        endDate = if(checkedOptions[1]) endDate else null
        testType = if(checkedOptions[2]) testType else null

        view.navToResultsSearchResults(patientID, startDate, endDate, testType)
    }


}