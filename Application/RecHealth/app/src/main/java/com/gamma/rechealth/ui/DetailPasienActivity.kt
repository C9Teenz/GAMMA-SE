package com.gamma.rechealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.adapter.DetailRiwayatPenyakitAdapter
import kotlinx.android.synthetic.main.activity_detail_pasien.*

class DetailPasienActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pasien)

        rcDiagnosis.adapter = DetailRiwayatPenyakitAdapter(this)

        fabCatatanMedis.setOnClickListener {
            startActivity(
                Intent(this, FormCatatanMedis::class.java)
            )
        }
    }
}