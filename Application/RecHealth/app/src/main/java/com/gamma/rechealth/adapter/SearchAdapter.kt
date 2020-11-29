package com.gamma.rechealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.DetailRiwayatPenyakit
import com.gamma.rechealth.R
import kotlinx.android.synthetic.main.item_jadwal_title.view.*
import kotlinx.android.synthetic.main.item_riwayat_penyakit.view.*

class SearchAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val PASIEN = 1
        const val TITLE = 2
        const val DIAGNOSIS = 3
    }

    class DiagnosisViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(context: Context) {
            view.itemRiwayatPenyakit.setOnClickListener {
                context.startActivity(
                    Intent(context, DetailRiwayatPenyakit::class.java)
                )
            }
        }

    }

    class TitleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(context: Context) {
            view.itemTitle.text = "Riwayat Penyakit"
        }
    }

    class PasienViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(context: Context) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DIAGNOSIS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_riwayat_penyakit, parent, false)
                DiagnosisViewHolder(view)
            }
            TITLE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal_title, parent, false)
                TitleViewHolder(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_pasien_active, parent, false)
                PasienViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position) {
            0 -> {
                (holder as PasienViewHolder).bindView(context)
            }
            1 -> {
                (holder as TitleViewHolder).bindView(context)
            }
            else -> {
                (holder as DiagnosisViewHolder).bindView(context)
            }
        }
    }

    override fun getItemCount(): Int = 7

    override fun getItemViewType(position: Int): Int {
        return when {
            position > 1 -> {
                DIAGNOSIS
            }
            position == 1 -> {
                TITLE
            }
            else -> {
                PASIEN
            }
        }
    }
}