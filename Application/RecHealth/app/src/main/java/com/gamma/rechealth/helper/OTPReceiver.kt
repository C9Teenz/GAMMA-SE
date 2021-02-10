package com.gamma.rechealth.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.widget.EditText
import com.chaos.view.PinView


class OTPReceiver : BroadcastReceiver() {

    private val TAG = OTPReceiver::class.java.simpleName
    private var otpReceiver: OTPReceiveListener? = null

    fun initOTPListener(receiver: OTPReceiveListener) {
        this.otpReceiver = receiver
    }
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>
                for (aPdusObj in pdusObj) {
                    val currentMessage = getIncomingMessage(aPdusObj, bundle)
                    val message = currentMessage.displayMessageBody
                    val otpCode = message.split(":")[1].trim()
                    Log.d(TAG, "onReceive: $otpCode")
                    otpReceiver!!.onOTPReceived(otpCode)
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception smsReceiver$e")
        }
    }



    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        currentSMS = if (Build.VERSION.SDK_INT >= 23) {
            val format = bundle.getString("format")
            SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else SmsMessage.createFromPdu(aObject as ByteArray)
        return currentSMS
    }

    interface OTPReceiveListener {

        fun onOTPReceived(otp: String)

        fun onOTPTimeOut()
    }
}