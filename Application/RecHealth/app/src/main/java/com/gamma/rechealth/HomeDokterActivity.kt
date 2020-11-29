package com.gamma.rechealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamma.rechealth.adapter.DaftarPasienAdapter
import kotlinx.android.synthetic.main.activity_home_dokter.*

class HomeDokterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dokter)

        rvPasien.adapter = DaftarPasienAdapter(this)

        btnSearch.setOnClickListener {
            startActivity(
                Intent(this, SearchActivity::class.java)
            )
        }
    }
}