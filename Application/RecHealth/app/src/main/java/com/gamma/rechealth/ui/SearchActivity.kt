package com.gamma.rechealth.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gamma.rechealth.R
import com.gamma.rechealth.adapter.SearchAdapter
import com.gamma.rechealth.firebase.firestore.FirestoreDisease
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnSearch.setOnClickListener {
            FirestoreUser.searchUser(etSearch.text.toString()){status, user ->
                if(status){
                    FirestoreDisease.getDiseasesById(user?.idUser!!){status, disease ->
                        val list = mutableListOf<Any>()
                        list.add(user)
                        list.add("")
                        list.addAll(disease)
                        rvSearch.adapter = SearchAdapter(this, list)
                    }
                } else {
                    Toast.makeText(this, "Data tidak ada", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}