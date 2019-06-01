package com.prezyk.patient_idifier.activity.view

interface QRCodeCaptureView {
    fun showModalWindowQRCaptured(patientID: Long)
    fun showToastDetectionFailure()
}