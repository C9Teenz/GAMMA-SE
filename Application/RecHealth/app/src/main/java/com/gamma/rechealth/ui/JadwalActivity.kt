package com.gamma.rechealth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.adapter.JadwalAdapter
import com.gamma.rechealth.model.Jadwal
import com.gamma.rechealth.model.Poli
import com.gamma.rechealth.ui.ConfirmCheckup
import kotlinx.android.synthetic.main.activity_jadwal.*

class JadwalActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SCHEDUL = "schedule"
        const val EXTRA_HOSPITAL = "hospital"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        val list = arrayListOf<Jadwal>()
        val schedule = intent.getParcelableExtra<Poli>(EXTRA_SCHEDUL) ?: Poli()
        val hospital = intent.getStringExtra(EXTRA_HOSPITAL) ?: "Umum"
        Log.d("TAG", "onCreate: $hospital")
        schedule.schedule.forEach {
            list.add(Jadwal(true, it.day))
            it.doctors.forEach { it ->
                list.add(Jadwal(false, it.name, it.time, it.id))
            }
        }

        rvJadwal.adapter = JadwalAdapter(list) {
            startActivity(
                Intent(
                    this,
                    ConfirmCheckup::class.java
                )
                    .putExtra(ConfirmCheckup.EXTRA_HOSPITAL, hospital)
                    .putExtra(ConfirmCheckup.EXTRA_POLI, schedule.poli)
                    .putExtra(ConfirmCheckup.EXTRA_SCHEDULE, it)


            )
        }
    }
}