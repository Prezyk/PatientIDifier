package com.prezyk.patient_idifier.time_series_result_display

import android.os.AsyncTask
import android.util.Log
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.model.TimeSeries
import com.prezyk.patient_idifier.model.TimeSeriesFactory
import com.prezyk.patient_idifier.web.APIInterface
import com.prezyk.patient_idifier.web.URL
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TSResultDisplayPresenter(var view: TSResultDisplayView) {

    lateinit var result: Result

    fun downloadData() {
        var retrofit = Retrofit.Builder().baseUrl(URL.URL).build()

        var apiInterface = retrofit.create(APIInterface::class.java)
        var call = apiInterface.doGetTimeSeriesResult(result.id)
        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("ERROR", t.message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                Log.e("RESPONSE", String(response.body()!!.bytes(), Charset.forName("UTF-8")))
                var async = object: AsyncTask<Void, Void, String>() {
                    var text: String? = null
                    override fun doInBackground(vararg params: Void?): String? {
                        return String(response.body()!!.bytes())
                    }

                    override fun onPostExecute(result: String?) {
                        super.onPostExecute(result)
                        view.updateCharts(TimeSeriesFactory.getTSArrayFromString(result!!))
                    }
                }
                async.execute()
            }

        })

    }

}