package com.gamma.rechealth.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gamma.rechealth.HomePasien
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verifikasi.*
import java.util.concurrent.TimeUnit

class VerifikasiActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "USER"
        const val TAG = "VerifikasiActivity"
    }

    var verificationId = ""
    var user : User? = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasi)


        user = intent.getParcelableExtra<User>(EXTRA_USER)
        Log.d(EXTRA_USER, user.toString())
        btnVerifikasi.setOnClickListener {
            verifyPhone()
        }


        val phoneNumber = if (user?.noTelephone?.get(0).toString() == "0") {
            val sb = StringBuilder(user?.noTelephone)
            sb.deleteCharAt(0).toString()
        } else {
            user?.noTelephone
        }

        Log.d(TAG, "onCreate: ${phoneNumber}")

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+62$phoneNumber",
            60,
            TimeUnit.SECONDS,
            this,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted: " + p0.smsCode)

                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d(TAG, "onVerificationFailed: " + p0.localizedMessage)
                }

                override fun onCodeSent(
                    verifId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verifId, token)
                    verificationId = verifId
                }

                override fun onCodeAutoRetrievalTimeOut(p0: String) {
                    super.onCodeAutoRetrievalTimeOut(p0)

                }
            }
        )
    }

    private fun verifyPhone() {
        val otpCode = pvOTP.text.toString()
        val credential = PhoneAuthProvider.getCredential(verificationId, otpCode)
        when {
            otpCode.isEmpty() -> Toast.makeText(this, "OTP Tidak Boleh Kosong", Toast.LENGTH_SHORT)
                .show()
            else -> {

                Auth.instance
                    .signInWithCredential(credential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val authUser = it.result?.user
                            user?.idUser = authUser?.uid!!
                            FirestoreUser.saveUser(user!!) { isExist, user ->
                                if (isExist) {
                                    startActivity(Intent(this, HomePasien::class.java))
                                    finishAffinity()
                                } else {
                                    Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        } else {
                            Toast.makeText(this, "Verifikasi OTP Gagal", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}