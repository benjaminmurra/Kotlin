package com.example.upnout.objects

import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

@Parcelize
class Event (
    val id: String,
    val title: String,
    val event_description: String,
    val organizer: String,
    val location: String,
    //val start_time: Timestamp,
    //val end_time: Timestamp
        ): Parcelable {

    companion object {
        fun DocumentSnapshot.toEvent(): Event? {
            try {
                val id = getString("id")!!
                val title = getString("first_name")!!
                val event_description = getString("last_name")!!
                val organizer = getString("email")!!
                val location = getString("location")!!
                //val start_time = getString("start_time")?.let { getTimestamp(it) }!!
                //val end_time = getString("end_time")?.let { getTimestamp(it) }!!
                return Event(id, title, event_description, organizer, location)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting event", e)
                return null
            }
        }
        private const val TAG = "User"
    }
}