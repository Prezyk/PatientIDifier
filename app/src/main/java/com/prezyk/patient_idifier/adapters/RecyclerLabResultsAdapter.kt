package com.prezyk.patient_idifier.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.model.LabResultsDTO

class RecyclerLabResultsAdapter(private val labResults: LabResultsDTO) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TITLE_ITEM = 0
    val DATA_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val row: View
        if(viewType==TITLE_ITEM) {
            row = LayoutInflater.from(parent.context).inflate(R.layout.lab_results_item_label, parent, false)
            return RecyclerLabResultsAdapter.LabResultHolderTitle(row)
        } else {
            row = LayoutInflater.from(parent.context).inflate(R.layout.lab_results_item_data, parent, false)
            return RecyclerLabResultsAdapter.LabResultHolderData(row)

        }
    }

    override fun getItemCount(): Int {
        return labResults.data.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(labResults.isTestGroupName[position]) TITLE_ITEM else DATA_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position)==TITLE_ITEM) {
            (holder as LabResultHolderTitle).textViewTestGroup.text = labResults.data[position][0]
        } else {
            (holder as LabResultHolderData).apply {
                textViewResultValue.text = labResults.data[position][0]
                textViewResultUnit.text = labResults.data[position][1]
                textViewLowerLimit.text = labResults.data[position][2]
                textViewUpperLimit.text = labResults.data[position][3]
                textViewTestName.text = labResults.data[position][4]
            }
        }

    }

    class LabResultHolderTitle(v: View) : RecyclerView.ViewHolder(v) {
        var textViewTestGroup : TextView = v.findViewById(R.id.textViewTestGroup)
    }

    class LabResultHolderData(v: View) : RecyclerView.ViewHolder(v) {
        var textViewResultValue : TextView = v.findViewById(R.id.textViewResultValue)
        var textViewResultUnit : TextView = v.findViewById(R.id.textViewResultUnit)
        var textViewTestName : TextView = v.findViewById(R.id.textViewTestName)
        var textViewLowerLimit : TextView = v.findViewById(R.id.textViewResultLowerLimit)
        var textViewUpperLimit : TextView = v.findViewById(R.id.textViewResultUpperLimit)
    }
}