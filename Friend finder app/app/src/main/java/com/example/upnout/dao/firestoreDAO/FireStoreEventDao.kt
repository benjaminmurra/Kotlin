package com.example.upnout.dao.firestoreDAO

import android.util.Log
import com.example.upnout.database.FirebaseConnector
import com.example.upnout.models.EventRoom
import com.google.firebase.firestore.ktx.toObject

class FireStoreEventDao {

    fun getEvents(firebaseConnector: FirebaseConnector) {
        firebaseConnector.get().collection("events").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Firestore", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "Error getting documents: ", exception)
            }
    }

    fun getEvent(firebaseConnector: FirebaseConnector, id: String) : EventRoom {
        var event = EventRoom("", "", "", "", "")
        firebaseConnector.get().collection("events").document(id).get()
            .addOnSuccessListener { documentSnapshot ->
                event = documentSnapshot.toObject<EventRoom>()!!
        }

        return event
    }

    //@Query("SELECT * FROM event_table ORDER BY RAND() LIMIT 1")
    fun getRandomEvent() {

    }

    //Will be used by the database to refresh the local storage
    //Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg events: EventRoom) {

    }

    //@Delete
    fun delete(event: EventRoom) {

    }
}