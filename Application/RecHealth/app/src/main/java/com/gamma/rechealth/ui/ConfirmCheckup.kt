package com.gamma.rechealth.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.firebase.firestore.FirestoreDisease
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.model.Jadwal
import com.gamma.rechealth.model.Penyakit
import com.gamma.rechealth.model.User
import kotlinx.android.synthetic.main.activity_confirm_checkup.*
import java.text.SimpleDateFormat
import java.util.*

class ConfirmCheckup : AppCompatActivity() {

    companion object {
        const val EXTRA_SCHEDULE = "extraschedule"
        const val EXTRA_POLI = "extrapoli"
        const val EXTRA_HOSPITAL = "extrahospital"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_checkup)

        val schedule = intent.getParcelableExtra<Jadwal>(EXTRA_SCHEDULE)
        val hospital = intent.getStringExtra(EXTRA_HOSPITAL) ?: "Umum"
        val poli = intent.getStringExtra(EXTRA_POLI) ?: "Umum"

        tvRumahSakit.text = hospital
        tvPoli.text = poli
        tvDokter.text = schedule?.title

        btnDaftar.setOnClickListener {

            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val currentDate = sdf.format(Date())

            FirestoreUser.getUser(User(Auth.currentUser()?.uid!!)) { status, user ->
                val penyakit = Penyakit(
                    rumahSakit = hospital,
                    poli = poli,
                    idPasien = Auth.currentUser()?.uid!!,
                    namaDokter = schedule?.title!!,
                    idDokter = schedule.id!!,
                    namaPasien = user?.nama!!
                )

                btnDaftar.text = "Loading.."

                FirestoreDisease.postDataDisease(penyakit) {
                    if (it) {
                        Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomePasien::class.java))
                        finishAffinity()
                    } else {
                        Toast.makeText(this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show()
                        btnDaftar.text = "Daftar"
                    }


                }
            }

        }


    }
}