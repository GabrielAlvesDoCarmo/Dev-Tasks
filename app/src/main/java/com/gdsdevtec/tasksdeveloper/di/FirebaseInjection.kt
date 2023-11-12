package com.gdsdevtec.tasksdeveloper.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

data object FirebaseInjection {

    fun getFirebaseAuth() = Firebase.auth
    fun getRealtime() = Firebase.database.reference
    fun getStorage() = Firebase.storage.reference
}