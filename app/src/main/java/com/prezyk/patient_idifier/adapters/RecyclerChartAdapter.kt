package com.prezyk.patient_idifier.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.model.TimeSeries

class RecyclerChartAdapter(private val timeSeries: Array<TimeSeries>) : RecyclerView.Adapter<RecyclerChartAdapter.TimeSeriesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSeriesHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.time_series_display_item, parent, false)
        return TimeSeriesHolder(row)
    }

    override fun getItemCount(): Int {
        return timeSeries.size
    }

    override fun onBindViewHolder(holder: TimeSeriesHolder, position: Int) {
        holder.lineChart.apply {
            setBackgroundColor(Color.WHITE)
            description.isEnabled = true
            description.text = timeSeries[position].title
            setTouchEnabled(true)
            setDrawGridBackground(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            isAutoScaleMinMaxEnabled = true
            setChartData(timeSeries[position], this)
        }

    }

    private fun setChartData(ts: TimeSeries, chart: LineChart) {
        var dataList = ArrayList<Entry>()
        for(i in 0 until ts.dataSeries[0].size) {
            dataList.add(Entry(ts.dataSeries[0][i].toFloat(), ts.dataSeries[1][i].toFloat()))
        }

        var dataSet: LineDataSet

        if(chart.data!=null) {
            dataSet = chart.data.getDataSetByIndex(0) as LineDataSet
            dataSet.values = dataList
            dataSet.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            dataSet = LineDataSet(dataList, ts.title)
            dataSet.setDrawCircleHole(false)
            dataSet.setDrawCircles(false)
            var dataSetList = arrayListOf<ILineDataSet>(dataSet)
            var data = LineData(dataSetList)
            chart.data = data

        }


    }

    class TimeSeriesHolder(v: View) : RecyclerView.ViewHolder(v) {
        var lineChart: LineChart = v.findViewById(R.id.lineChart) as LineChart

    }
}