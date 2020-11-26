package com.gamma.rechealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.DetailArtikel
import com.gamma.rechealth.R
import kotlinx.android.synthetic.main.item_artikel.view.*

class ArtikelAdapter(val context: Context) : RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(context: Context){
        itemView.itemArtikel.setOnClickListener {
            val intent = Intent(context, DetailArtikel::class.java)
            context.startActivity(intent)
        }
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context)
    }

    override fun getItemCount(): Int {
        return 7
    }
}