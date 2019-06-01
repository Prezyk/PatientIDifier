package com.prezyk.patient_idifier.activity

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.*
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.activity.view.QRCodeCaptureView
import com.prezyk.patient_idifier.presenter.QRCodeCapturePresenter
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import kotlinx.android.synthetic.main.qr_code_capture.*
import kotlin.math.max



class QRCodeCaptureActivity: AppCompatActivity(), QRCodeCaptureView, LifecycleOwner {

    private lateinit var detector: FirebaseVisionBarcodeDetector
    private val REQUEST_CODE_PERMISSIONS = 10

    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    private lateinit var presenter: QRCodeCapturePresenter

    private lateinit var imageCapture: ImageCapture



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_code_capture)

        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
            .build()

        this.detector = FirebaseVision.getInstance().visionBarcodeDetector

        this.presenter = QRCodeCapturePresenter(this)


        if (allPermissionsGranted()) {
            textureView.post { startCamera() }

            buttonRecognizeQR.setOnClickListener {
                Log.e("BUTTON", "CLICKED")

                imageCapture.takePicture(object : ImageCapture.OnImageCapturedListener() {
                    override fun onCaptureSuccess(image: ImageProxy?, rotationDegrees: Int) {

                        var capturedImage = image!!.image!!
                        var degrees = rotationDegrees

                        if(image != null) {
                            Log.e("CAPTURE", "SUCCESS")
                        }

                        var rotation = when(degrees) {
                            0 -> FirebaseVisionImageMetadata.ROTATION_0
                            90 -> FirebaseVisionImageMetadata.ROTATION_90
                            180 -> FirebaseVisionImageMetadata.ROTATION_180
                            270 -> FirebaseVisionImageMetadata.ROTATION_270
                            else -> FirebaseVisionImageMetadata.ROTATION_90
                        }


                        var firebaseImage = FirebaseVisionImage.fromMediaImage(capturedImage, rotation)

                        var result = detector.detectInImage(firebaseImage)
                            .addOnSuccessListener {
                                //                            if(it.isNotEmpty()) {
//                                Log.e("DETECTOR", it.first().rawValue.toString())
//                                presenter.capturedQRCode(it.first().rawValue)
//                            }

                                if(it.isNotEmpty()) {
                                    presenter.capturedQRCode(it.first().rawValue)
                                } else {
                                    showToastDetectionFailure()
                                }
                                for(b: FirebaseVisionBarcode in it) {
                                    Log.e("BARCODE", b.rawValue.toString())
                                }

                            }.addOnFailureListener {
                                Log.e("DETECTOR", "FAILED")
                            }
                        image.close()
                        capturedImage.close()
                    }
                })



            }



        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        textureView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            updateTransform()
        }





//        buttonRecognizeQR.setOnClickListener {
//
////            var image = FirebaseVisionImage.fromBitmap(textureView.)
//
//            detector.detectInImage(image)
//                .addOnSuccessListener {
//                    if(it.isNotEmpty()) {
//                        Log.e("DETECTOR", it.first().rawValue.toString())
//                        presenter.capturedQRCode(it.first().rawValue)
//                    }
//
//                }.addOnFailureListener {
//                    Log.e("DETECTOR", "FAILED")
//                }
//
//
//        }

    }

    private fun startCamera() {
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            var size = max(textureView.height, textureView.width)
            setTargetResolution(Size(640, 640))
        }.build()



        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {

            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            parent.addView(textureView, 0)

            textureView.surfaceTexture = it.surfaceTexture

            updateTransform()
        }

        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .setTargetRotation(windowManager.defaultDisplay.rotation)
            .setTargetResolution(Size(480, 480))
            .build()

//        val imageCapture = ImageCapture(imageCaptureConfig)
        this.imageCapture = ImageCapture(imageCaptureConfig)

        CameraX.bindToLifecycle(this, preview, imageCapture)

    }

    private fun updateTransform() {

        val matrix = Matrix()

        val centerX = textureView.width / 2f
        val centerY = textureView.height / 2f

        val rotationDegrees = when(textureView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }


        var scaleX = 1.0f
        var scaleY = 1.0f

        var cameraHeight = 640
        var cameraWidth = 640

        if (cameraHeight > textureView.width && cameraWidth > textureView.height) {
            scaleX = cameraHeight / textureView.width.toFloat()
            scaleY = cameraWidth / textureView.height.toFloat()
        } else if (cameraHeight < textureView.width && cameraWidth < textureView.height) {
            scaleY = textureView.width.toFloat() / cameraHeight
            scaleX = textureView.height.toFloat() / cameraWidth
        } else if (textureView.width > cameraHeight) {
            scaleY = (textureView.width.toFloat() / cameraHeight) / (textureView.height / cameraWidth)
        } else if (textureView.height > cameraWidth) {
            scaleX = (textureView.height.toFloat() / cameraWidth) / (textureView.width / cameraHeight)
        }

        var pivotX = textureView.width.toFloat()/2
        var pivotY = textureView.height.toFloat()/2

        Log.e("SCALING_CROPPING", "NANA")
        Log.e("SCALE_X", scaleX.toString())
        Log.e("SCALE_Y", scaleY.toString())

        matrix.setScale(scaleX, scaleY, pivotX, pivotY)
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)
        textureView.setTransform(matrix)


    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if(allPermissionsGranted()) {
                textureView.post { startCamera() }
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun showModalWindowQRCaptured(patientID: Long) {

        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(getString(R.string.dialog_qr_code_detected_title) + patientID.toString())
                setMessage(R.string.dialog_qr_code_detected_message)
                setPositiveButton(R.string.dialog_qr_code_detected_ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.e("DIALOG_WINDOW", "OK_CLICKED")
                        navigateToPatientPreviewActivity(patientID)
                    })

                setNegativeButton(R.string.dialog_qr_code_detected_cancel,
                    DialogInterface.OnClickListener { dialog, id -> Log.e("DIALOG_WINDOW", "CANCEL_CLICKED") })
            }

            builder.create()
        }
        alertDialog?.show()

    }

    override fun showToastDetectionFailure() {
        var toast = Toast.makeText(this, R.string.toast_invalid_barcode, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun navigateToPatientPreviewActivity(patientID: Long) {
        var intent = Intent(this, PatientPreviewActivity::class.java)
        intent.putExtra("id", patientID)
        startActivity(intent)
    }
}