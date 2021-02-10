package com.gamma.rechealth.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gamma.rechealth.R
import com.gamma.rechealth.model.Article
import com.gamma.rechealth.ui.DetailArtikel
import kotlinx.android.synthetic.main.item_artikel.view.*

class ArtikelAdapter(val articles: List<Article>, val context: Context) :
    RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article, context: Context) {
            itemView.apply {
                Glide.with(this).load(article.image).into(ivArticle)
                tvTitle.text = article.title
                tvContent.text = article.content
                itemArtikel.setOnClickListener {
                    val intent = Intent(
                        context,
                        DetailArtikel::class.java
                    ).putExtra(DetailArtikel.EXTRA_ARTICLE, article)
                    context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position], context)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}