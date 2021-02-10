package com.gamma.rechealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamma.rechealth.adapter.DetailRiwayatPenyakitAdapter
import com.gamma.rechealth.model.Penyakit
import com.gamma.rechealth.ui.FormCatatanMedis
import kotlinx.android.synthetic.main.activity_detail_riwayat_penyakit.*

class DetailRiwayatPenyakit : AppCompatActivity() {
    companion object {
        const val EXTRA_PENYAKIT = "asas"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_riwayat_penyakit)

        val penyakit = intent.getParcelableExtra<Penyakit>(EXTRA_PENYAKIT)

        rcDiagnosis.adapter = DetailRiwayatPenyakitAdapter(this, penyakit?.dataKontrol!!)

    }
}