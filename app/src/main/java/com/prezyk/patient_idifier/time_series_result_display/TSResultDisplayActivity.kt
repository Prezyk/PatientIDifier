package com.prezyk.patient_idifier.time_series_result_display

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.adapters.RecyclerChartAdapter
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.model.TimeSeries
import java.util.*

class TSResultDisplayActivity: AppCompatActivity(), TSResultDisplayView {

    lateinit var presenter: TSResultDisplayPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_series_display)
        presenter = TSResultDisplayPresenter(this)

//        presenter.result = intent.extras.getSerializable("result") as Result
        presenter.result = Result(1L, "", Date(), "")
        presenter.downloadData()

    }

    override fun updateCharts(tsArray: Array<TimeSeries>) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = RecyclerChartAdapter(tsArray)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTimeSeries).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
}