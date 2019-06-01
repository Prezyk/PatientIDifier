package com.prezyk.patient_idifier.presenter

import com.prezyk.patient_idifier.activity.view.QRCodeCaptureView
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class QRCodeCapturePresenter(var view: QRCodeCaptureView) {



    fun capturedQRCode(data: String?) {
        var patientID: Long? = try { data?.toLong() } catch(e1: NumberFormatException) {null} catch (e2: IllegalArgumentException) {null}
        if(patientID!=null) {
            view.showModalWindowQRCaptured(patientID)
        } else {
            view.showToastDetectionFailure()
        }
    }

}