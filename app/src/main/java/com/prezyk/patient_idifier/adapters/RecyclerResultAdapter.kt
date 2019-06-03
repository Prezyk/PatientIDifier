package com.prezyk.patient_idifier.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.image_result_display.ImageResultDisplayActivity
import com.prezyk.patient_idifier.lab_results_display.LabResultsDisplayActivity
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.time_series_result_display.TSResultDisplayActivity
import java.text.SimpleDateFormat

class RecyclerResultAdapter(private val results: List<Result>): RecyclerView.Adapter<RecyclerResultAdapter.ResultHolder>() {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    lateinit var context: Context
    lateinit var parent: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.results_search_display_item, parent, false)
        this.context = parent.context
        this.parent = parent
        return RecyclerResultAdapter.ResultHolder(row)

    }

    override fun getItemCount(): Int {
        return results.size
    }



    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        holder.textViewResultID.text = results[position].id.toString()
        holder.textViewResultDate.text = dateFormat.format(results[position].date)
        holder.textViewResultTestType.text = results[position].testType
        holder.textViewDescription.text = results[position].description

        holder.itemView.setOnClickListener {
            when(results[position].testType) {
                "IMG" -> {
                    var intent = Intent(context, ImageResultDisplayActivity::class.java).apply {
                        putExtra("result", results[position])
                    }
                    context.startActivity(intent)
                }

                "TS" -> {
                    var intent = Intent(context, TSResultDisplayActivity::class.java).apply {
                        putExtra("result", results[position])
                    }
                    context.startActivity(intent)
                }

                "LAB" -> {
                    var intent = Intent(context, LabResultsDisplayActivity::class.java).apply {
                        putExtra("result", results[position])
                    }
                    context.startActivity(intent)

                }
            }
        }
    }

    class ResultHolder(v: View): RecyclerView.ViewHolder(v) {



        var textViewResultID: TextView = v.findViewById(R.id.textViewID) as TextView
        var textViewResultDate: TextView = v.findViewById(R.id.textViewDate) as TextView
        var textViewResultTestType: TextView = v.findViewById(R.id.textViewTestType) as TextView
        var textViewDescription: TextView = v.findViewById(R.id.textViewDescription) as TextView


    }
}