package com.example.upnout.database

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseConnector() {
    private lateinit var db: FirebaseFirestore

    init {
        db = FirebaseFirestore.getInstance()
    }

    fun get(): FirebaseFirestore {
        return db
    }
}