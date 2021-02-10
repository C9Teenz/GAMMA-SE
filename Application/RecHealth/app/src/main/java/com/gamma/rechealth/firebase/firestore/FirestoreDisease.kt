package com.gamma.rechealth.firebase.firestore

import com.gamma.rechealth.firebase.auth.Auth
import com.gamma.rechealth.model.Penyakit
import com.google.firebase.firestore.SetOptions

object FirestoreDisease {
    val collection = "disease"
    fun getDiseases(callback: (Boolean, List<Penyakit>) -> Unit) {
        val idUser = Auth.currentUser()?.uid
        FirestoreInstance.instance.collection(collection).whereEqualTo("idPasien", idUser).get()
            .addOnSuccessListener {
                callback(true, it.toObjects(Penyakit::class.java))
            }
            .addOnFailureListener {
                callback(false, listOf())
            }
    }

    fun getDiseasesById(idUser: String, callback: (Boolean, List<Penyakit>) -> Unit) {
        FirestoreInstance.instance.collection(collection).whereEqualTo("idPasien", idUser).get()
            .addOnSuccessListener {
                callback(true, it.toObjects(Penyakit::class.java))
            }
            .addOnFailureListener {
                callback(false, listOf())
            }
    }

    fun postDataDisease(penyakit: Penyakit, callback: (Boolean) -> Unit) {
        FirestoreInstance.instance.collection(collection).document().set(penyakit)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun updateDisease(penyakit: Penyakit, callback: (Boolean) -> Unit) {
        FirestoreInstance.instance.collection(collection).document(penyakit.id)
            .set(penyakit, SetOptions.merge())
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun getSchedule(callback: (Boolean, List<Penyakit>) -> Unit) {
        val idUser = Auth.currentUser()?.uid
        FirestoreInstance.instance.collection(collection).whereEqualTo("idDokter", idUser)
            .whereEqualTo("statusPeriksa", true).get()
            .addOnSuccessListener {
                callback(true, it.toObjects(Penyakit::class.java))
            }
            .addOnFailureListener {
                callback(false, listOf())
            }
    }
}