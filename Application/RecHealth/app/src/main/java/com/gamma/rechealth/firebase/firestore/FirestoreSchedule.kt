package com.gamma.rechealth.firebase.firestore

import com.gamma.rechealth.model.Schedule

object FirestoreSchedule {
    val collection = "schedule"
    fun getSchedule(callback: (Boolean, List<Schedule>) -> Unit) {
        FirestoreInstance.instance.collection(collection).get()
            .addOnSuccessListener {
                callback(true, it.toObjects(Schedule::class.java))
            }
            .addOnFailureListener {
                callback(false, listOf())
            }
    }


}