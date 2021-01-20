package com.gamma.rechealth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.adapter.JadwalAdapter
import com.gamma.rechealth.model.Jadwal
import kotlinx.android.synthetic.main.activity_jadwal.*

class JadwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        val list = arrayListOf<Jadwal>()
        list.add(Jadwal(true, "Senin"))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(true, "Selasa"))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(true, "Rabu"))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(true, "Kamis"))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(true, "Jumat"))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))
        list.add(Jadwal(false, ""))

        rvJadwal.adapter = JadwalAdapter(list)
    }
}