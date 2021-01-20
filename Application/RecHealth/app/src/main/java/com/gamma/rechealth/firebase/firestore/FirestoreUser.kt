package com.gamma.rechealth.firebase.firestore

import com.gamma.rechealth.model.User

object FirestoreUser {
    val collection = "users"
    fun saveUser(user: User, callback: (Boolean, User) -> Unit) {
        FirestoreInstance.instance.collection(collection).document().set(user)
            .addOnSuccessListener {
                callback(true, user)
            }
            .addOnFailureListener {
                callback(false, user)
            }
    }
}