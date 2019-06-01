package com.prezyk.patient_idifier.presenter

import android.util.Log
import com.prezyk.patient_idifier.web.URL
import com.prezyk.patient_idifier.model.Patient
import com.prezyk.patient_idifier.activity.view.PatientPreviewView
import com.prezyk.patient_idifier.web.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PatientPreviewPresenter(var view: PatientPreviewView) : Callback<Patient>{

    lateinit var patient: Patient
    //TODO zdjęcie pacjenta, najlepiej jako base64(?)

    fun downloadData(patientID: Long) {
        view.showProgressBar()
        var retrofit = Retrofit.Builder().baseUrl(URL.URL).addConverterFactory(GsonConverterFactory.create()).build()


        var apiInterface = retrofit.create(APIInterface::class.java)
        var call = apiInterface.doGetPatientInfo(patientID)
        call.enqueue(this)


    }

    fun onSearchButtonClicked() {
        view.navToSearchResults(patient.id!!)
    }

    override fun onFailure(call: Call<Patient>, t: Throwable) {
        Log.e("MESSAGE", t.localizedMessage)
        Log.e("MESSAGE", t.message)


        view.showErrorMessage()
        view.returnToQRCodeCapturActivity()
    }

    override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
        view.hideProgressBar()
        if(response.body() == null) {
            view.showErrorMessage()
            view.returnToQRCodeCapturActivity()
        }
        this.patient = response.body()!!

        view.updateTextData(response.body()!!.getData())
    }
}