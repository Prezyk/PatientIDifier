package com.prezyk.patient_idifier.qr_rec

interface QRCodeCaptureView {
    fun showModalWindowQRCaptured(patientID: Long)
    fun showToastDetectionFailure()
}