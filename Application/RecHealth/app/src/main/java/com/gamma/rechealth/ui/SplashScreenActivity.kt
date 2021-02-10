package com.gamma.rechealth.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.HalamanAwal
import com.gamma.rechealth.HomeDokterActivity
import com.gamma.rechealth.OnBoardingActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.helper.SharedPreferences
import com.gamma.rechealth.model.User

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val auth = Auth.currentUser()
            if (auth != null) {
                FirestoreUser.getUser(User(auth.uid)) { status, user ->
                    if (status) {
                        if(user?.role == "USER"){
                            startActivity(
                                Intent(this, HomePasien::class.java)
                            )
                            finishAffinity()
                        } else {
                            startActivity(
                                Intent(this, HomeDokterActivity::class.java)
                            )
                            finishAffinity()
                        }
                    } else {
                        Toast.makeText(this, "Kesalahan", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

            } else {
                if (SharedPreferences.isFirst(this)) {
                    startActivity(
                        Intent(
                            this, OnBoardingActivity::class.java
                        )
                    )
                } else {
                    startActivity(
                        Intent(
                            this, HalamanAwal::class.java
                        )
                    )
                }

            }

        }, 2000)
    }
}