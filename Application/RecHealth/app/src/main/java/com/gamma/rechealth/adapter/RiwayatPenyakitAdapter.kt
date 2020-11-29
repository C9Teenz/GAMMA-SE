package com.gamma.rechealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.DetailRiwayatPenyakit
import com.gamma.rechealth.R
import kotlinx.android.synthetic.main.item_riwayat_penyakit.view.*

class RiwayatPenyakitAdapter(val context: Context) :
    RecyclerView.Adapter<RiwayatPenyakitAdapter.ViewHolder>() {
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context) {
            itemView.itemRiwayatPenyakit.setOnClickListener {
                val intent = Intent(context, DetailRiwayatPenyakit::class.java)
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
        holder.bind(context)
    }

    override fun getItemCount(): Int {
        return 7
    }
}