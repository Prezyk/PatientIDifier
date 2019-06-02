package com.prezyk.patient_idifier.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R

class RecyclerSeriesAdapter(private val series: Array<String>, var checkedOptions: BooleanArray):
    RecyclerView.Adapter<RecyclerSeriesAdapter.SeriesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.select_series_item, parent, false)
        return SeriesHolder(row)
    }

    override fun getItemCount(): Int {
        return series.size
    }

    override fun onBindViewHolder(holder: SeriesHolder, position: Int) {
        holder.checkBox.text = series[position]
        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            checkedOptions[position] = isChecked
        }
    }


    class SeriesHolder(v: View): RecyclerView.ViewHolder(v) {
        var checkBox: CheckBox = v.findViewById<CheckBox>(R.id.checkBoxSeries)
    }
}