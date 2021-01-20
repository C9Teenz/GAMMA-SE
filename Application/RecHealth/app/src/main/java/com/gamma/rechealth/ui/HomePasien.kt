package com.gamma.rechealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamma.rechealth.adapter.ArtikelAdapter
import kotlinx.android.synthetic.main.activity_home_pasien.*

class HomePasien : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pasien)

        val adapter = ArtikelAdapter(this)
        rvRecyclerView.adapter = adapter
        rvRecyclerView.layoutManager = LinearLayoutManager(this)

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
    }
}