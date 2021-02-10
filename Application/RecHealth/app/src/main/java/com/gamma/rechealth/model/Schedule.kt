package com.gamma.rechealth.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    @DocumentId
    val id: String = "",
    val hospital: String = "",
    val polies: List<Poli> = listOf()
) : Parcelable

@Parcelize
data class Poli(
    val poli: String = "",
    val schedule: List<DoctorSchedule> = listOf()
) : Parcelable

@Parcelize
data class DoctorSchedule(
    val day: String = "",
    val doctors: List<Doctor> = listOf()
) : Parcelable


@Parcelize
data class Doctor(
    val id: String = "",
    val name: String = "",
    val time: String = ""
) : Parcelable