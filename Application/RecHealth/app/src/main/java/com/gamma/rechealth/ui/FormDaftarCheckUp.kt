package com.gamma.rechealth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.adapter.CheckupFragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_form_daftar_check_up.*

class FormDaftarCheckUp : AppCompatActivity() {

    val title = arrayOf("Riwayat Penyakit", "Daftar Baru")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar_check_up)


        pager.adapter = CheckupFragmentAdapter(this)
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = title[position]
        }.attach()


    }
}