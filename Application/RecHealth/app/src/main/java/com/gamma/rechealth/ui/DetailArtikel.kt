package com.gamma.rechealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gamma.rechealth.R
import com.gamma.rechealth.model.Article
import kotlinx.android.synthetic.main.activity_detail_artikel.*

class DetailArtikel : AppCompatActivity() {
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artikel)

        val extra = intent.getParcelableExtra<Article>(EXTRA_ARTICLE)
        extra.let {
            Glide.with(this).load(it?.image).into(ivDetail)
            toolbar.title = it?.title
            tvDesc.text = it?.content
        }
    }
}