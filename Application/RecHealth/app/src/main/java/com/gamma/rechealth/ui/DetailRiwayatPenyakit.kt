package com.gamma.rechealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamma.rechealth.adapter.DetailRiwayatPenyakitAdapter
import kotlinx.android.synthetic.main.activity_detail_riwayat_penyakit.*

class DetailRiwayatPenyakit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat_penyakit)

        rcDiagnosis.adapter = DetailRiwayatPenyakitAdapter(this)

    }
}