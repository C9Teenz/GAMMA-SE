package com.gamma.rechealth.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.R
import com.gamma.rechealth.model.DataKontrol
import kotlinx.android.synthetic.main.item_diagnosis.view.*

class DetailRiwayatPenyakitAdapter(
    val context: Context,
    val dataKontrol: List<DataKontrol>
) :
    RecyclerView.Adapter<DetailRiwayatPenyakitAdapter.ViewHolder>() {


    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bulan = arrayOf(
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
        )

        fun bind(context: Context, dataKontrol: DataKontrol) {
            itemView.apply {
                tvSistolik.text = "${dataKontrol.tekananDarahSistolik} mmHg"
                tvDiastolik.text = "${dataKontrol.tekananDarahDiastolik} mmHg"
                tvGula.text = "${dataKontrol.kadarGula} md/dL"
                tvBerat.text = "${dataKontrol.beratBadan} kg"
                tvStatus.text = dataKontrol.status
                tvCatatan.text = dataKontrol.catatan

                val tanggal = dataKontrol.tanggalPeriksa.split("-")
                tvTanggal.text = tanggal[0]
                tvBulan.text = bulan[Integer.parseInt(tanggal[1]) - 1]
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_diagnosis, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, dataKontrol[position])
    }

    override fun getItemCount(): Int {
        return dataKontrol.size
    }
}
