package com.gamma.rechealth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.firestore.FirestoreDisease
import com.gamma.rechealth.model.DataKontrol
import com.gamma.rechealth.model.Penyakit
import kotlinx.android.synthetic.main.activity_form_catatan_medis.*
import java.text.SimpleDateFormat
import java.util.*

class FormCatatanMedis : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "asdasd"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_catatan_medis)

        val dataPenyakit = intent.getParcelableExtra<Penyakit>(EXTRA_DATA)



        btnSubmit.setOnClickListener {
            val sistolik = etSistolik.text
            val diastolik = etDiastolik.text
            val gula = etGula.text
            val berat = etBerat.text
            val penyakit = etPenyakit.text
            val catatan = etCatatan.text
            val status = etStatus.text


            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val currentDate = sdf.format(Date())

            dataPenyakit?.statusPeriksa = false
            dataPenyakit?.diperiksaPada = currentDate
            if (penyakit.toString() != "") {
                dataPenyakit?.penyakit = penyakit.toString()
            }
            dataPenyakit?.dataKontrol?.add(
                DataKontrol(
                    berat.toString().toInt(),
                    catatan.toString(),
                    gula.toString().toInt(),
                    status.toString(),
                    currentDate,
                    diastolik.toString().toInt(),
                    sistolik.toString().toInt()
                )
            )
            FirestoreDisease.updateDisease(dataPenyakit!!) {

            }

        }

    }
}