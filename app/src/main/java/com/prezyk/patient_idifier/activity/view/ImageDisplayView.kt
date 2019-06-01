package com.prezyk.patient_idifier.activity.view

import android.graphics.Bitmap

interface ImageDisplayView {
    fun showProgessBar()
    fun hideProgressBar()
    fun updateImageView(image: Bitmap)
    fun showErrorMessage()
}