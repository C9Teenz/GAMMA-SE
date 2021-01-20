package com.gamma.rechealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.model.User
import com.gamma.rechealth.ui.VerifikasiActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.pvOTP
import kotlinx.android.synthetic.main.activity_verifikasi.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    var verificationId = ""
    var user : User? = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnMasuk.setOnClickListener {
            verifyPhone()
        }
        Log.d(VerifikasiActivity.EXTRA_USER, user.toString())
        btnarrow.setOnClickListener {
            if(TextUtils.isEmpty(etPhoneNumber.text)){
                Toast.makeText(this, "Isi cok", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val phoneNumber = if (etPhoneNumber.text[0].toString() == "0") {
                val sb = StringBuilder(etPhoneNumber.text)
                sb.deleteCharAt(0).toString()
            } else {
                etPhoneNumber.text
            }

            Log.d(VerifikasiActivity.TAG, "onCreate: ${phoneNumber}")
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62$phoneNumber",
                60,
                TimeUnit.SECONDS,
                this,
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        Log.d(VerifikasiActivity.TAG, "onVerificationCompleted: " + p0.smsCode)

                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        Log.d(VerifikasiActivity.TAG, "onVerificationFailed: " + p0.localizedMessage)
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
                            FirestoreUser.getUser(user!!) { isExist, user ->
                                if (isExist) {
                                    startActivity(Intent(this, HomePasien::class.java))
                                    finishAffinity()
                                } else {
                                    Toast.makeText(this, "Nomor Telephone Belum Terdaftar", Toast.LENGTH_SHORT)
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