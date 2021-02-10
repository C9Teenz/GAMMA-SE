package com.gamma.rechealth.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Penyakit(
    @DocumentId
    val id: String = "",
    val rumahSakit: String = "",
    val poli: String = "",
    var penyakit: String = "",
    val idPasien: String = "",
    val idDokter: String = "",
    val namaDokter: String = "",
    val namaPasien: String = "",
    var diperiksaPada: String = "",
    val dataKontrol: MutableList<DataKontrol> = mutableListOf(),
    var statusPeriksa: Boolean = true
) : Parcelable

@Parcelize
data class DataKontrol(
    val beratBadan: Int = 0,
    val catatan: String = "",
    val kadarGula: Int = 0,
    val status: String = "",
    val tanggalPeriksa: String = "",
    val tekananDarahDiastolik: Int = 0,
    val tekananDarahSistolik: Int = 0
) : Parcelable