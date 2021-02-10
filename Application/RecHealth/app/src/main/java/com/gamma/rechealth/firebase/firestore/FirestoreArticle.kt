package com.gamma.rechealth.firebase.firestore

import com.gamma.rechealth.model.Article
import com.gamma.rechealth.model.User

object FirestoreArticle {
    val collection = "articles"
    fun getArticles(callback: (Boolean, List<Article>) -> Unit) {
        FirestoreInstance.instance.collection(collection).get()
            .addOnSuccessListener {
                callback(true, it.toObjects(Article::class.java))
            }
            .addOnFailureListener {
                callback(false, listOf())
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