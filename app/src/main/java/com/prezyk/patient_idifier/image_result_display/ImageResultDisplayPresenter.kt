package com.prezyk.patient_idifier.image_result_display

import android.graphics.BitmapFactory
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.web.APIInterface
import com.prezyk.patient_idifier.web.URL
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ImageResultDisplayPresenter(var view: ImageResultDisplayView) : Callback<ResponseBody> {

    lateinit var result: Result


    fun downloadImageResult() {
//        var resultID = 3L
        view.showProgessBar()
        var retrofit = Retrofit.Builder().baseUrl(URL.URL).build()

        var apiInterface = retrofit.create(APIInterface::class.java)
        var call = apiInterface.doGetImageResult(result.id)
        call.enqueue(this)




    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        view.showErrorMessage()
//        Log.e("ERROR", "ON_FAILURE")
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        view.hideProgressBar()
        if(response.body()==null) {
            view.showErrorMessage()
        } else if(response.isSuccessful) {
            var imageBytes = response.body()!!.bytes()
//            var imageBytes = ByteArray(4096)
//            var read: Int
//            while(true) {
//                read = imageStream.read(imageBytes)
//                imageBytes = ByteArray(4096)
//                if(read==-1){
//                    break
//                }
//            }
            var bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes!!.size)
            view.updateImageView(bitmap)
        }

    }



//    private fun convertImage(inBytes: ByteArray): ByteArray {
//
//    }
}