package com.gamma.rechealth.ui

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gamma.rechealth.HalamanAwal
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.helper.OTPReceiver
import com.gamma.rechealth.model.User
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = User(
            idUser = Auth.currentUser()!!.uid
        )
        FirestoreUser.getUser(user) { status, user ->
            user?.apply {
                tvIdentity.text = user.noKTP
                tvName.text = user.nama
                tvPhone.text = user.noTelephone
                tvBirthdate.text = user.tanggalLahir
                tvGender.text = user.jenisKelamin
            }
        }

        btnKeluar.setOnClickListener {
            Auth.logout()
            startActivity(
                Intent(this, HalamanAwal::class.java)
            )
            finishAffinity()
        }


    }



}