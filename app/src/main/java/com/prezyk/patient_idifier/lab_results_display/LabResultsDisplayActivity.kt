package com.prezyk.patient_idifier.lab_results_display

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.adapters.RecyclerLabResultsAdapter
import com.prezyk.patient_idifier.model.LabResultsDTO
import com.prezyk.patient_idifier.model.Result
import kotlinx.android.synthetic.main.lab_results_display.*
import java.util.*

class LabResultsDisplayActivity: AppCompatActivity(), LabResultsDisplayView {
    lateinit var presenter: LabResultsDisplayPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: LinearLayoutManager
    //TODO labele + wyśrodkowanie recyclerview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lab_results_display)

        presenter = LabResultsDisplayPresenter(this)
//        presenter.result = Result(2L, "", Date(), "")
        presenter.result = intent.extras.getSerializable("result") as Result
        presenter.downloadData()


    }


    override fun updateTextViewDescription(text: String) {
        textViewLabResultDescription.text = text
    }

    override fun updateRecyclerViewResults(resultsDTO: LabResultsDTO) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerLabResultsAdapter(resultsDTO)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLabResults).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun showProgressBar() {
        nestedScrollView.visibility = View.GONE
        progressBar2.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        nestedScrollView.visibility = View.VISIBLE
        progressBar2.visibility = View.GONE
    }
}