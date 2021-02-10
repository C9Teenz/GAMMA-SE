package com.gamma.rechealth.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jadwal(
    val isTitle: Boolean,
    val title: String?,
    val time: String? = "",
    val id: String? = ""
) : Parcelable