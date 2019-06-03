package com.prezyk.patient_idifier.lab_results_display

import com.prezyk.patient_idifier.model.LabResultsDTO

interface LabResultsDisplayView {
    fun updateTextViewDescription(text: String)
    fun updateRecyclerViewResults(resultsDTO: LabResultsDTO)
    fun showProgressBar()
    fun hideProgressBar()
}