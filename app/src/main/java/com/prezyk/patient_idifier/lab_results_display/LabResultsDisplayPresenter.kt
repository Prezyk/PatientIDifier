package com.prezyk.patient_idifier.lab_results_display

import android.os.AsyncTask
import android.util.Log
import com.prezyk.patient_idifier.model.LabResultsDTO
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.model.TimeSeriesFactory
import com.prezyk.patient_idifier.model.json.LabResults
import com.prezyk.patient_idifier.web.APIInterface
import com.prezyk.patient_idifier.web.URL
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LabResultsDisplayPresenter(var view: LabResultsDisplayView) {

    lateinit var result: Result

    fun downloadData() {
        view.showProgressBar()
        view.updateTextViewDescription(result.description)
        var retrofit = Retrofit.Builder().baseUrl(URL.URL).addConverterFactory(GsonConverterFactory.create()).build()

        var apiInterface = retrofit.create(APIInterface::class.java)
        var call = apiInterface.doGetLabResult(result.id)
        call.enqueue(object: Callback<LabResults> {
            override fun onFailure(call: Call<LabResults>, t: Throwable) {
                Log.e("ERROR", t.message)
            }

            override fun onResponse(call: Call<LabResults>, response: Response<LabResults>) {
//                Log.e("RESPONSE", String(response.body()!!.bytes(), Charset.forName("UTF-8")))
                var async = object: AsyncTask<Void, Void, LabResultsDTO>() {
                    var text: String? = null
                    override fun doInBackground(vararg params: Void?): LabResultsDTO? {
                        return LabResultsDTO(response.body()!!)
                    }

                    override fun onPostExecute(result: LabResultsDTO?) {
                        super.onPostExecute(result)
                        view.updateRecyclerViewResults(result!!)
                        view.hideProgressBar()
//                        timeSeries = TimeSeriesFactory.getTSArrayFromString(result!!)
//                        view.hideProgressBar()
//                        view.updateCharts(TimeSeriesFactory.getTSArrayFromString(result!!))
                    }
                }
                async.execute()
            }

        })
    }

}