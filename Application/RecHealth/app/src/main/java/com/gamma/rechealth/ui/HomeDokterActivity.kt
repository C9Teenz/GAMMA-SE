package com.gamma.rechealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamma.rechealth.adapter.DaftarPasienAdapter
import com.gamma.rechealth.firebase.firestore.FirestoreDisease
import com.gamma.rechealth.ui.ProfileActivity
import com.gamma.rechealth.ui.SearchActivity
import kotlinx.android.synthetic.main.activity_home_dokter.*
import kotlinx.android.synthetic.main.activity_home_dokter.btnProfile

class HomeDokterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dokter)

        FirestoreDisease.getSchedule { b, list ->
            rvPasien.adapter = DaftarPasienAdapter(this, list)
        }

        btnSearch.setOnClickListener {
            startActivity(
                Intent(this, SearchActivity::class.java)
            )
        }

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}