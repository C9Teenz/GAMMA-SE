package com.gamma.rechealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_verifikasi.*

class VerifikasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi)

        btnVerifikasi.setOnClickListener {
            val intent = Intent(this, HomeDokterActivity::class.java)
            startActivity(intent)
        }
    }
}