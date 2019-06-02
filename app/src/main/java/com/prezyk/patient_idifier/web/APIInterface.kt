package com.prezyk.patient_idifier.web

import com.prezyk.patient_idifier.model.Patient
import com.prezyk.patient_idifier.model.Result
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming

interface APIInterface {

    @GET("/data/patientinfo")
    fun doGetPatientInfo(@Query("patientID") patientID: Long): Call<Patient>

    @GET("/data/patient/results")
    fun doGetResultsData(@Query("patientID") patientID: Long,
                         @Query("startDate") startDate: Long?,
                         @Query("endDate") endDate: Long?,
                         @Query("testType") testType: String?): Call<List<Result>>

    @GET("/data/patient/imageresult")
    fun doGetImageResult(@Query("resultID") resultID: Long) : Call<ResponseBody>

    @GET("/data/patient/timeseriesresult")
    @Streaming
    fun doGetTimeSeriesResult(@Query("resultID") resultID: Long) : Call<ResponseBody>
}