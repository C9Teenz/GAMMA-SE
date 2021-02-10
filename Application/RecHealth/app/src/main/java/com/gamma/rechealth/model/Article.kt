package com.gamma.rechealth.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val image: String = ""
) : Parcelable