package com.prezyk.patient_idifier.found_results

import com.prezyk.patient_idifier.model.Result

interface ResultsSearchResultsView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showErrorMessage()
    fun updateRecyclerView(results: List<Result>)
}