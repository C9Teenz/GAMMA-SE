package com.gamma.rechealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.DetailPasienActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.model.Penyakit
import kotlinx.android.synthetic.main.item_pasien.view.*
import kotlinx.android.synthetic.main.item_pasien_active.view.*

class DaftarPasienAdapter(val context: Context, val list: List<Penyakit>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ACTIVE = 1
        const val NONACTIVE = 2
    }

    class ActiveViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(context: Context, penyakit: Penyakit) {

            view.tvPasien.text = penyakit.namaPasien
            view.tvPenyakitAktif.text =
                if (penyakit.penyakit == "") "Belum Diketahui" else penyakit.penyakit
            view.itemPasienActive.setOnClickListener {
                context.startActivity(
                    Intent(
                        context,
                        DetailPasienActivity::class.java
                    ).putExtra(DetailPasienActivity.EXTRA_PENYAKT, penyakit)
                )
            }
        }

    }

    class NonActiveViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(context: Context, penyakit: Penyakit) {
            view.apply {
                tvPasienn.text = penyakit.namaPasien
                tvPenyakitt.text =
                    if (penyakit.penyakit == "") "Belum Diketahui" else penyakit.penyakit
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ACTIVE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pasien_active, parent, false)
            ActiveViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_pasien, parent, false)
            NonActiveViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (position == 0) {
            true -> {
                (holder as ActiveViewHolder).bindView(context, list[position])
            }
            else -> {
                (holder as NonActiveViewHolder).bindView(context, list[position])
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            ACTIVE
        } else {
            NONACTIVE
        }
    }
}