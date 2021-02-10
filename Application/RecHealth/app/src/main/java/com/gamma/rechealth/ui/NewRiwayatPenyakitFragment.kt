package com.gamma.rechealth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.gamma.rechealth.JadwalActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.firestore.FirestoreSchedule
import com.gamma.rechealth.model.Poli
import com.gamma.rechealth.model.Schedule
import kotlinx.android.synthetic.main.fragment_new_riwayat_penyakit.*

class NewRiwayatPenyakitFragment : Fragment() {
    var listSchedule = listOf<Schedule>()
    var dataPoli = Poli()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_riwayat_penyakit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirestoreSchedule.getSchedule { b, list ->
            listSchedule = list
            val hospital = arrayListOf<String>()
            listSchedule.forEach {
                hospital.add(it.hospital)
            }

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                hospital
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnHospital.adapter = adapter

            spnHospital.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    hospital: Int,
                    p3: Long
                ) {
                    val polies = arrayListOf<String>()
                    listSchedule[hospital].polies.forEach {
                        polies.add(it.poli)
                    }
                    val adapterPoli = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        polies
                    )
                    adapterPoli.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spnPoli.adapter = adapterPoli

                    spnPoli.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            poli: Int,
                            p3: Long
                        ) {
                            dataPoli = listSchedule[hospital].polies[poli]
                            Log.d("TAG", "onItemSelected: $poli")
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }

                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }


        }
        btnDaftar.setOnClickListener {
            Log.d("TAG", "onViewCreated: ${spnHospital.selectedItem}")
            startActivity(
                Intent(
                    requireContext(),
                    JadwalActivity::class.java
                ).putExtra(JadwalActivity.EXTRA_SCHEDUL, dataPoli)
                    .putExtra(JadwalActivity.EXTRA_HOSPITAL, spnHospital.selectedItem.toString())
            )
        }
    }
}