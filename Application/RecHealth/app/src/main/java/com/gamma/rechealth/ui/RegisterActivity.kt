package com.gamma.rechealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.ui.VerifikasiActivity.Companion.EXTRA_USER
import com.gamma.rechealth.model.User
import com.gamma.rechealth.ui.VerifikasiActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnDaftar.setOnClickListener {
            val user = User()
            user.noKTP = etNoKtp.text.toString()
            user.noTelephone = etNomorTelepon.text.toString()
            user.nama = etNama.text.toString()
            user.jenisKelamin = etJenisKelamin.selectedItem.toString()
            user.tanggalLahir = etTanggalLahir.text.toString()

            val intent = Intent(this, VerifikasiActivity::class.java).putExtra(EXTRA_USER, user)
            startActivity(intent)
        }
    }
}