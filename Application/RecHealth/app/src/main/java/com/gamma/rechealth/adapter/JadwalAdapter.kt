package com.gamma.rechealth.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamma.rechealth.R
import com.gamma.rechealth.model.Jadwal
import kotlinx.android.synthetic.main.item_jadwal_title.view.*

class JadwalAdapter(val list: ArrayList<Jadwal>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TITLE = 1
        const val CONTENT = 2
    }

    class TitleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(jadwal: Jadwal) {
            view.itemTitle.text = jadwal.title
        }

    }

    class ContentViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(jadwal: Jadwal) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TITLE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_jadwal_title, parent, false)
            TitleViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal, parent, false)
            ContentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(list[position].isTitle){
            true -> {
                (holder as TitleViewHolder).bindView(list[position])
            }
            else -> {
                (holder as ContentViewHolder).bindView(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].isTitle) {
            TITLE
        } else {
            CONTENT
        }
    }
}