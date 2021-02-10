package com.gamma.rechealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.DetailRiwayatPenyakit
import com.gamma.rechealth.R
import com.gamma.rechealth.model.Penyakit
import kotlinx.android.synthetic.main.item_riwayat_penyakit.view.*

class RiwayatPenyakitAdapter(val context: Context, val listPenyakit: List<Penyakit>) :
    RecyclerView.Adapter<RiwayatPenyakitAdapter.ViewHolder>() {
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, penyakit: Penyakit) {
            itemView.tvPenyakit.text =
                if (penyakit.penyakit != "") penyakit.penyakit else "Belum Diketahui"

            itemView.tvTanggalPeriksa.text =
                if (penyakit.diperiksaPada == "") "-" else penyakit.diperiksaPada

            itemView.tvPoli.text = penyakit.poli
            itemView.tvRumahSakit.text = penyakit.rumahSakit
            itemView.tvDokter.text = penyakit.namaDokter


            itemView.itemRiwayatPenyakit.setOnClickListener {
                val intent = Intent(context, DetailRiwayatPenyakit::class.java).putExtra(
                    DetailRiwayatPenyakit.EXTRA_PENYAKIT,
                    penyakit
                )
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_riwayat_penyakit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, listPenyakit[position])
    }

    override fun getItemCount(): Int = listPenyakit.size
}