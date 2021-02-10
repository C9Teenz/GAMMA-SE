package com.gamma.rechealth.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamma.rechealth.DaftarRiwayatPenyakitActivity
import com.gamma.rechealth.MapsActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.adapter.ArtikelAdapter
import com.gamma.rechealth.firebase.firestore.FirestoreArticle
import kotlinx.android.synthetic.main.activity_home_pasien.*

class HomePasien : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pasien)

        FirestoreArticle.getArticles { b, list ->
            val adapter = ArtikelAdapter(list,this)
            rvRecyclerView.adapter = adapter
            rvRecyclerView.layoutManager = LinearLayoutManager(this)

        }

        fabDaftar.setOnClickListener {
            val intent = Intent(this, FormDaftarCheckUp::class.java)
            startActivity(intent)
        }

        btnRiwayatPenyakit.setOnClickListener {
            val intent = Intent(this, DaftarRiwayatPenyakitActivity::class.java)
            startActivity(intent)
        }

        btnMap.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}