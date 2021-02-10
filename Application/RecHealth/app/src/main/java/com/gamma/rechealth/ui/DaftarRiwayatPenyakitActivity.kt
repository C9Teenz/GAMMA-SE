package com.gamma.rechealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamma.rechealth.adapter.RiwayatPenyakitAdapter
import com.gamma.rechealth.firebase.firestore.FirestoreDisease
import kotlinx.android.synthetic.main.activity_daftar_riwayat_penyakit.*

class DaftarRiwayatPenyakitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_riwayat_penyakit)

        FirestoreDisease.getDiseases { b, list ->

            rvRiwayatPenyakit.adapter = RiwayatPenyakitAdapter(this, list)
        }

    }
}