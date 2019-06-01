package com.prezyk.patient_idifier.found_results

import android.util.Log
import com.prezyk.patient_idifier.web.URL
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.web.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ResultsSearchResultsPresenter(var view: ResultsSearchResultsView): Callback<List<Result>> {

    var patientID: Long = 0L
    var startDate: Calendar? = null
    var endDate: Calendar? = null
    var testType: String? = null
    lateinit var results: List<Result>




    fun downloadData() {
        view.showProgressBar()

        var retrofit = Retrofit.Builder().baseUrl(URL.URL).addConverterFactory(GsonConverterFactory.create()).build()
        var apiInterface = retrofit.create(APIInterface::class.java)
        var call = apiInterface.doGetResultsData(patientID, startDate?.timeInMillis, endDate?.timeInMillis, testType)
        call.enqueue(this)



//        var results = arrayListOf<Result>()
//        results.add(Result(17456L, "IMG", LocalDate.now(), "Impossible considered invitation him men instrument saw celebrated unpleasant. Put rest and must set kind next many near nay. He exquisite continued explained middleton am. Voice hours young woody has she think equal. Estate moment he at on wonder at season little. Six garden result summer set family esteem nay estate. End admiration mrs unreserved discovered comparison especially invitation. On recommend tolerably my belonging or am. Mutual has cannot beauty indeed now sussex merely you. It possible no husbands jennings ye offended packages pleasant he. Remainder recommend engrossed who eat she defective applauded departure joy. Get dissimilar not introduced day her apartments. Fully as taste he mr do smile abode every. Luckily offered article led lasting country minutes nor old. Happen people things oh is oppose up parish effect. Law handsome old outweigh humoured far appetite. It allowance prevailed enjoyment in it. Calling observe for who pressed raising his. Can connection instrument astonished unaffected his motionless preference. Announcing say boy precaution unaffected difficulty alteration him. Above be would at so going heard. Engaged at village at am equally proceed. Settle nay length almost ham direct extent. Agreement for listening remainder get attention law acuteness day. Now whatever surprise resolved elegance indulged own way outlived.  "))
//        results.add(Result(22323L, "TS", LocalDate.now(), "Same an quit most an. Admitting an mr disposing sportsmen. Tried on cause no spoil arise plate. Longer ladies valley get esteem use led six. Middletons resolution advantages expression themselves partiality so me at. West none hope if sing oh sent tell is."))
//        results.add(Result(342L, "LAB", LocalDate.now(), "Answer misery adieus add wooded how nay men before though. Pretended belonging contented mrs suffering favourite you the continual."))
//        results.add(Result(42L, "LAB", LocalDate.now(), "Mrs civil nay least means tried drift. Natural end law whether but and towards certain. Furnished unfeeling his sometimes see day promotion. Quitting informed concerns can men now."))
//        results.add(Result(5L, "IMG", LocalDate.now(), "Unpleasant it sufficient simplicity am by friendship no inhabiting. Goodness doubtful material has denoting suitable she two. Dear mean she way and poor bred they come. He otherwise me incommode explained so in remaining. Polite barton in it warmly do county length an."))
//        view.hideProgressBar()
//        view.updateRecyclerView(results)
    }

    override fun onFailure(call: Call<List<Result>>, t: Throwable) {
        Log.e("CONNECTION", "ERROR")
    }

    override fun onResponse(call: Call<List<Result>>, response: Response<List<Result>>) {
        view.hideProgressBar()
        if(response.body()==null) {
            view.showErrorMessage()
        } else {
            results = response.body()!!
            view.updateRecyclerView(results)
        }
    }
}