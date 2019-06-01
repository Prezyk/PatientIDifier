package com.prezyk.patient_idifier.search_results

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.found_results.ResultsSearchResultsActivity
import kotlinx.android.synthetic.main.results_search.*
import java.util.*

class SearchResultsActivity(): AppCompatActivity(), SearchResultsView {

    lateinit var presenter: SearchResultsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_search)

        this.presenter = SearchResultsPresenter(this)
        spinnerTestType.isEnabled = false

        presenter.patientID = intent.extras.getLong("id")
        Log.e("PATIENT_ID", presenter.patientID.toString())


        buttonSearch.setOnClickListener {
            var id = spinnerTestType.selectedItemPosition
            presenter.testType = resources.getStringArray(R.array.test_types)[id]
            presenter.onSearchButtonClicked()
        }

        checkBoxStartDate.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.setStartDateOption(isChecked)
            if(isChecked) {
                enableTextViewStartDate()
            } else {
                disableTextViewStartDate()
            }
        }

        checkBoxEndDate.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.setEndDateOption(isChecked)
            if(isChecked) {
                enableTextViewEndDate()
            } else {
                disableTextViewEndDate()
            }
        }

        checkBoxTestType.setOnCheckedChangeListener { buttonView, isChecked ->
            presenter.setTestTypeOption(isChecked)
            if(isChecked) {
                enableSpinnerTestType()
            } else {
                disableSpinnerTestType()
            }
        }

    }

    override fun enableTextViewStartDate() {
        textViewStartDate.isEnabled=true
        textViewStartDate.setOnClickListener {
            presenter.startDateTextViewClicked()
        }
    }

    override fun disableTextViewStartDate() {
        textViewStartDate.isEnabled=false
        textViewStartDate.setOnClickListener { null }
    }

    override fun enableTextViewEndDate() {
        textViewEndDate.isEnabled=true
        textViewEndDate.setOnClickListener {
            presenter.endDateTextViewClicked()
        }
    }

    override fun disableTextViewEndDate() {
        textViewEndDate.isEnabled=false
        textViewEndDate.setOnClickListener { null }
    }

    override fun enableSpinnerTestType() {
        spinnerTestType.isEnabled=true
    }

    override fun disableSpinnerTestType() {
        spinnerTestType.isEnabled=false
    }

    override fun updateTextViewStartDate(text: String) {
        textViewStartDate.text = text
    }

    override fun updateTextViewEndDate(text: String) {
        textViewEndDate.text = text
    }

    override fun showStartDatePicker(sYear: Int, sMonth: Int, sDay: Int) {
        var startDatePickerDialog = DatePickerDialog(this, R.style.MySpinnerDatePickerStyle,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                presenter.setStartDate(dayOfMonth, month, year)
            }, sYear, sMonth, sDay)
        startDatePickerDialog.show()
    }

    override fun showEndDatePicker(sYear: Int, sMonth: Int, sDay: Int) {
        var endDatePickerDialog = DatePickerDialog(this, R.style.MySpinnerDatePickerStyle,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                presenter.setEndDate(dayOfMonth, month, year)
            }, sYear, sMonth, sDay)
        endDatePickerDialog.show()
    }

    override fun navToResultsSearchResults(patientID: Long, startDate: Calendar?, endDate: Calendar?, testType: String?) {
        var intent = Intent(this, ResultsSearchResultsActivity::class.java).apply {
            putExtra("id", patientID)
            putExtra("startDate", startDate)
            putExtra("endDate", endDate)
            putExtra("testType", testType)
        }
        startActivity(intent)
    }


}