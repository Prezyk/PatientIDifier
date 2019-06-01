package com.prezyk.patient_idifier.time_series_result_display

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prezyk.patient_idifier.R

class TSResultDisplayActivity: AppCompatActivity(), TSResultDisplayView {

    lateinit var presenter: TSResultDisplayPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_series_display)

        presenter = TSResultDisplayPresenter(this)

    }
}