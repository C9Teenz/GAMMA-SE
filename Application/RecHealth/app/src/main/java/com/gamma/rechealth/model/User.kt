package com.gamma.rechealth.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @DocumentId
    var idUser: String = "",
    var noKTP: String = "",
    var noTelephone: String = "",
    var nama: String = "",
    var jenisKelamin: String = "",
    var tanggalLahir: String = "",
    var role: String = "USER"
) : Parcelable