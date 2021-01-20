package com.gamma.rechealth.firebase.firestore

import com.gamma.rechealth.model.User

object FirestoreUser {
    val collection = "users"
    fun saveUser(user: User, callback: (Boolean, User) -> Unit) {
        FirestoreInstance.instance.collection(collection).document(user.idUser).set(user)
            .addOnSuccessListener {
                callback(true, user)
            }
            .addOnFailureListener {
                callback(false, user)
            }
    }

    fun getUser(user: User, callback: (Boolean, User?) -> Unit) {
        FirestoreInstance.instance.collection(collection).document(user.idUser).get()
            .addOnSuccessListener {
                    if(it.exists()){
                        val user = it.toObject(User::class.java)
                        callback(true, user)
                    } else {
                        callback(false, null)
                    }
            }
            .addOnFailureListener {
                callback(false, null)
            }
    }
}