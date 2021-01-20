package com.gamma.rechealth.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object Auth {
    val instance = FirebaseAuth.getInstance()

    fun currentUser(): FirebaseUser? {
        return instance.currentUser
    }

    fun logout() {
        instance.signOut()
    }
}