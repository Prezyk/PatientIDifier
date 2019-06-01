package com.prezyk.patient_idifier.patient_preview

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prezyk.patient_idifier.R
import com.prezyk.patient_idifier.search_results.SearchResultsActivity
import kotlinx.android.synthetic.main.patient_preview.*

class PatientPreviewActivity: AppCompatActivity(),
    PatientPreviewView {

    lateinit var presenter: PatientPreviewPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_preview)

        presenter = PatientPreviewPresenter(this)

        var patientID = intent.extras.get("id") as Long
        Log.e("PATIENT_ID", patientID.toString())

        presenter.downloadData(patientID)


    }

    override fun updateTextData(data: Array<String?>) {
        textViewPatientFName.text = data[0]
        textViewPatientLName.text = data[1]
        textViewPatientSex.text = when(data[2]) {
            "WOMAN" -> getString(R.string.gender_woman)
            "MAN" -> getString(R.string.gender_man)
            else -> ""
        }

        textViewPatientBirthdate.text = data[3]
        textViewPatientPhoneNumber.text = data[4]
        textViewPatientAlergies.text = data[5]

    }

    override fun updateImage(image: Bitmap) {
        imageViewPatientPhoto.setImageBitmap(image)
    }

    override fun showProgressBar() {
        setContentView(R.layout.progress_bar_layout)
    }

    override fun hideProgressBar() {
        setContentView(R.layout.patient_preview)
        buttonResults.setOnClickListener {
            presenter.onSearchButtonClicked()
        }

    }

    override fun navToSearchResults(patientID: Long) {
        val intent = Intent(this, SearchResultsActivity::class.java).apply {
            putExtra("id", patientID)
        }
        startActivity(intent)
    }

    override fun showErrorMessage() {
        var toast = Toast.makeText(this, "CONNECTION ERROR", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun returnToQRCodeCapturActivity() {
        finish()
    }
}