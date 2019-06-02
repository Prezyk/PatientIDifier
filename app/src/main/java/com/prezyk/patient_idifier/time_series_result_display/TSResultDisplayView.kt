package com.prezyk.patient_idifier.time_series_result_display

import com.prezyk.patient_idifier.model.TimeSeries

interface TSResultDisplayView {
    fun updateCharts(tsArray: List<TimeSeries>)
    fun showProgressBar()
    fun hideProgressBar()
    fun updateDescriptionText(text: String)
    fun navToSelectOptions(titles: Array<String>)
}