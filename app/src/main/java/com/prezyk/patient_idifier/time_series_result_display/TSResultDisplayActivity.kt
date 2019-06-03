package com.prezyk.patient_idifier.time_series_result_display

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.model.TimeSeries
import com.prezyk.patient_idifier.time_series_result_display.series_options_check.SelectSeriesActivity
import kotlinx.android.synthetic.main.time_series_display.*
import java.text.DecimalFormat
import java.util.*
import com.github.mikephil.charting.formatter.IValueFormatter as IValueFormatter1

class TSResultDisplayActivity: AppCompatActivity(), TSResultDisplayView {

    lateinit var presenter: TSResultDisplayPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var lineChart: LineChart
    val REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_series_display)
        presenter = TSResultDisplayPresenter(this)
        lineChart = findViewById(R.id.lineChart) as LineChart

        presenter.result = intent.extras.getSerializable("result") as Result
//        presenter.result = Result(1L, "", Date(), "")
        presenter.downloadData()

        buttonSelectOptions.setOnClickListener {
            presenter.onSelectOptionsButtonClicked()
        }

    }

    override fun updateCharts(tsArray: List<TimeSeries>) {

//TODO dopicowanie wykresu
        //TODO napisy na elementach

        lineChart.apply {
            setBackgroundColor(Color.WHITE)
            description.isEnabled = false
//            description.text = timeSeries[position].title
            setTouchEnabled(true)
            setDrawGridBackground(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)

//            isAutoScaleMinMaxEnabled = true
            setChartData(tsArray, this)

        }

    }

    private fun setChartData(tsArray: List<TimeSeries>, chart: LineChart) {
        var dataSetList = ArrayList<ILineDataSet>()

        for(j in 0 until tsArray.size) {
            var dataList = ArrayList<Entry>()
            for (i in 0 until tsArray[j].dataSeries[0].size) {
                dataList.add(Entry(tsArray[j].dataSeries[0][i].toFloat()/1000, tsArray[j].dataSeries[1][i].toFloat()/1000))
            }

            var dataSet: LineDataSet

//        if(chart.data!=null) {
//            dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
//            dataSet.values = dataList
//            dataSet.notifyDataSetChanged()
//            chart.data.notifyDataChanged()
//            chart.notifyDataSetChanged()
//        } else {
            dataSet = LineDataSet(dataList, tsArray[j].title)
            dataSet.setDrawCircleHole(false)
            dataSet.setDrawCircles(false)
            dataSet.color = ChartColors.getColorArray()[j]
            dataSetList.add(dataSet)
        }
            var data = LineData(dataSetList)
            chart.data = data


//
    }

    override fun showProgressBar() {
        scrollViewTS.visibility = View.GONE
        lineChart.visibility = View.GONE
        progressBarTS.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        scrollViewTS.visibility = View.VISIBLE
        lineChart.visibility = View.VISIBLE
        progressBarTS.visibility = View.GONE
    }

    override fun updateDescriptionText(text: String) {
        textViewTSDescription.text = text
    }

    override fun navToSelectOptions(titles: Array<String>) {
        var intent = Intent(this, SelectSeriesActivity::class.java).apply {
            putExtra("seriesTitles", titles)
        }
        startActivityForResult(intent, REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE) {
            if(resultCode== Activity.RESULT_OK) {
                presenter.acvitityResultReceived(data?.extras?.getBooleanArray("selectedOptions") ?: BooleanArray(1) {i -> false})
            }
        }
    }
}