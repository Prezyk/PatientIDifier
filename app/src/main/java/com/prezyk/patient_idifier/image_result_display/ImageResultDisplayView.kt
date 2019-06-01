package com.prezyk.patient_idifier.image_result_display

import android.graphics.Bitmap

interface ImageResultDisplayView {
    fun showProgessBar()
    fun hideProgressBar()
    fun updateImageView(image: Bitmap)
    fun showErrorMessage()
}