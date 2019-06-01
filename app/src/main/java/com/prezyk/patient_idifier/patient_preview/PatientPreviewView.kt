package com.prezyk.patient_idifier.patient_preview

import android.graphics.Bitmap

interface PatientPreviewView {

    fun updateTextData(data: Array<String?>)
    fun updateImage(image: Bitmap)
    fun showProgressBar()
    fun hideProgressBar()
    fun navToSearchResults(patientID: Long)
    fun showErrorMessage()
    fun returnToQRCodeCapturActivity()

}