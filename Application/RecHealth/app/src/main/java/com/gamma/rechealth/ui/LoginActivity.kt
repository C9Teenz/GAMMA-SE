package com.gamma.rechealth.ui

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.gamma.rechealth.HomeDokterActivity
import com.gamma.rechealth.R
import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.firebase.firestore.FirestoreUser
import com.gamma.rechealth.helper.OTPReceiver
import com.gamma.rechealth.model.User
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.pvOTP
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity(), OTPReceiver.OTPReceiveListener  {
    var verificationId = ""
    var user : User? = User()
    private var smsReceiver: OTPReceiver? = null
    companion object {
        private const val SMS_REQUEST_CODE = 101
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        requestPermissionSMS()
        startSMSListener()
        btnMasuk.setOnClickListener {
            verifyPhone()
        }
        Log.d(VerifikasiActivity.EXTRA_USER, user.toString())
        btnarrow.setOnClickListener {
            if(TextUtils.isEmpty(etPhoneNumber.text)){
                Toast.makeText(this, "Isi dulu", Toast.LENGTH_SHORT).show()
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

    private fun requestPermissionSMS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS),
                SMS_REQUEST_CODE
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
                                    if(user?.role == "USER"){
                                        startActivity(Intent(this, HomePasien::class.java))
                                        finishAffinity()
                                    } else if(user?.role == "DOKTER") {
                                        startActivity(Intent(this, HomeDokterActivity::class.java))
                                        finishAffinity()
                                    }
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

    private fun startSMSListener() {
        try {
            smsReceiver = OTPReceiver()
            smsReceiver!!.initOTPListener(this)

            val intentFilter = IntentFilter()
            intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION)
            this.registerReceiver(smsReceiver, intentFilter)

            val client = SmsRetriever.getClient(this)

            val task = client.startSmsRetriever()
            task.addOnSuccessListener {
                // API successfully started
            }

            task.addOnFailureListener {
                // Fail to start API
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    override fun onOTPReceived(otp: String) {
        pvOTP.setText(otp)
    }

    override fun onOTPTimeOut() {
        TODO("Not yet implemented")
    }

}