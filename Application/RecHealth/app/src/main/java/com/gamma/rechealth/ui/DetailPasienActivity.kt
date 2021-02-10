package com.gamma.rechealth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.adapter.DetailRiwayatPenyakitAdapter
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.model.Penyakit
import com.gamma.rechealth.model.User
import com.gamma.rechealth.ui.FormCatatanMedis
import kotlinx.android.synthetic.main.activity_detail_pasien.*

class DetailPasienActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PENYAKT = "penyakit"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pasien)


        val penyakit = intent.getParcelableExtra<Penyakit>(EXTRA_PENYAKT) ?: Penyakit()
        FirestoreUser.getUser(User(penyakit.idPasien)){status, user ->
            tvNama.text = user?.nama
        }

        rcDiagnosis.adapter = DetailRiwayatPenyakitAdapter(this, penyakit.dataKontrol)

        fabCatatanMedis.setOnClickListener {
            startActivity(
                Intent(this, FormCatatanMedis::class.java).putExtra(FormCatatanMedis.EXTRA_DATA, penyakit)
            )
        }
    }
}