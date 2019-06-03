package com.prezyk.patient_idifier.found_results

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.adapters.RecyclerResultAdapter
import com.prezyk.patient_idifier.model.Result
import java.util.*

class ResultsSearchResultsActivity() : AppCompatActivity(),
    ResultsSearchResultsView {

    lateinit var presenter: ResultsSearchResultsPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_search_display)
        presenter = ResultsSearchResultsPresenter(this)


        presenter.patientID = intent.extras.getLong("id")
        presenter.startDate = intent.extras.getSerializable("startDate") as Calendar?
        presenter.endDate = intent.extras.getSerializable("endDate") as Calendar?
        presenter.testType = intent.extras.getString("testType")

//        Log.e("STARTDATE", startDate!!.toString("dd.MM.yyyy"))
//        Log.e("ENDDATE", endDate!!.toString("dd.MM.yyyy"))
//        Log.e("TESTTYPE", testType)

        presenter.downloadData()


    }


    override fun showProgressBar() {
        setContentView(R.layout.progress_bar_layout)
    }

    override fun hideProgressBar() {
        setContentView(R.layout.results_search_display)
    }

    override fun updateRecyclerView(results: List<Result>) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerResultAdapter(results)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewResults).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun showErrorMessage() {
        var toast = Toast.makeText(this, "CONNECTION ERROR", Toast.LENGTH_SHORT)
        toast.show()
        finish()
    }

}