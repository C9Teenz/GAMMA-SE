package com.gamma.rechealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gamma.rechealth.adapter.ArtikelAdapter
import kotlinx.android.synthetic.main.activity_home_pasien.*

class HomePasien : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pasien)

        val adapter = ArtikelAdapter()
        rvRecyclerView.adapter = adapter
        rvRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}