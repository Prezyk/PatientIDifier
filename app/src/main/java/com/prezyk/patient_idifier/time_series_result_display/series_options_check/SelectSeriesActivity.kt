package com.prezyk.patient_idifier.time_series_result_display.series_options_check

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.adapters.RecyclerResultAdapter
import com.prezyk.patient_idifier.adapters.RecyclerSeriesAdapter
import kotlinx.android.synthetic.main.results_search.*
import kotlinx.android.synthetic.main.select_series.*

class SelectSeriesActivity: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var selectedOptions: BooleanArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_series)

        viewManager = LinearLayoutManager(this)
        var options = intent.extras.getStringArray("seriesTitles")
        selectedOptions = BooleanArray(options.size)
        viewAdapter = RecyclerSeriesAdapter(options, selectedOptions)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSelectSeries).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


        buttonSubmitOptions.setOnClickListener {
            var intent = Intent()
            intent.putExtra("selectedOptions", selectedOptions)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }



    }
}