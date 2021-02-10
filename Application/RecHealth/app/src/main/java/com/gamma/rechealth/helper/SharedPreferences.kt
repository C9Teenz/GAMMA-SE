package com.gamma.rechealth.helper

import android.content.Context
import android.content.SharedPreferences

object SharedPreferences {
    const val isFirst = "isfirts"
    lateinit var sharedPreferences: SharedPreferences
    private fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("RECHEALTH", Context.MODE_PRIVATE)
    }

    fun isFirst(context: Context): Boolean {
        init(context)
        return sharedPreferences.getBoolean(isFirst, true)
    }

    fun setFirst(context: Context, boolean: Boolean) {
        sharedPreferences.edit().putBoolean(isFirst, boolean).apply()
    }
}