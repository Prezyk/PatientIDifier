package com.prezyk.patient_idifier.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.activity.view.ImageDisplayView
import com.prezyk.patient_idifier.model.Result
import com.prezyk.patient_idifier.presenter.ImageDisplayPresenter
import kotlinx.android.synthetic.main.imaging_display.*
import kotlin.math.max
import kotlin.math.min




class ImageDisplayActivity : AppCompatActivity(), ImageDisplayView {

    //TODO poprawić interaktywność zdjęcia
    private var mScaleFactor = 1.0f
    private lateinit var imageViewImagingDisplay: ImageView
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    lateinit var presenter: ImageDisplayPresenter
//    lateinit var textViewDescription: TextView
//    lateinit var progressBarImageDisplay: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imaging_display)
        presenter = ImageDisplayPresenter(this)
        presenter.result = intent.extras.getSerializable("result") as Result
        imageViewImagingDisplay = findViewById(R.id.imageViewImagingDisplay)
//        textViewDescription = findViewById(R.id.textViewDescription)
//        progressBarImageDisplay = findViewById(R.id.progressBarImageDisplay)
        textViewImageDisplayDescription.text = presenter.result.description


        val scaleGestureDetector = object: ScaleGestureDetector.SimpleOnScaleGestureListener() {

            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                mScaleFactor *= detector!!.scaleFactor

                mScaleFactor = max(0.1f, min(mScaleFactor, 10.0f))
                imageViewImagingDisplay.scaleX = mScaleFactor
                imageViewImagingDisplay.scaleY = mScaleFactor
                return true

            }
        }

        mScaleGestureDetector = ScaleGestureDetector(this, scaleGestureDetector)

        imageViewImagingDisplay.setOnTouchListener { v, event ->
            mScaleGestureDetector.onTouchEvent(event)
        }


        presenter.downloadImageResult()


    }

    override fun showProgessBar() {

        imageViewImagingDisplay.visibility = View.INVISIBLE
        textViewImageDisplayDescription.visibility = View.INVISIBLE
        progressBarImageDisplay.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        imageViewImagingDisplay.visibility = View.VISIBLE
        textViewImageDisplayDescription.visibility = View.VISIBLE
        progressBarImageDisplay.visibility = View.GONE
    }

    override fun updateImageView(image: Bitmap) {
        imageViewImagingDisplay.setImageBitmap(image)
    }

    override fun showErrorMessage() {
        var toast = Toast.makeText(this, "CONNECTION ERROR", Toast.LENGTH_SHORT)
        toast.show()
    }

    //    private class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//
//        override fun onScale(detector: ScaleGestureDetector?): Boolean {
//
//
//
//
//
//            return true
//        }
//    }

}