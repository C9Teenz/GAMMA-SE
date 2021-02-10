package com.gamma.rechealth.ui


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.model.User
import com.gamma.rechealth.ui.VerifikasiActivity.Companion.EXTRA_USER
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        etTanggalLahir.setOnClickListener {
            val dpd = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                etTanggalLahir.setText("$dayOfMonth-$month-$year")
            }, year, month, day)
            dpd.show()
        }
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
