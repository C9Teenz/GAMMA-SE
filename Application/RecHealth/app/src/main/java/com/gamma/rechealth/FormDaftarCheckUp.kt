package com.gamma.rechealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_form_daftar_check_up.*

class FormDaftarCheckUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar_check_up)

        btnDaftar.setOnClickListener {
            startActivity(Intent(this, JadwalActivity::class.java))
        }
    }
}