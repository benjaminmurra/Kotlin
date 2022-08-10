package com.example.upnout.objects

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot

@Parcelize
class User (
    val id: String,
    val full_name: String,
    val email: String,
    val phone_number: String,
    val location: String,
    val profile_completed: Int = 0
        ) : Parcelable {

    companion object {
        fun DocumentSnapshot.toUser(): User? {
            try {
                val id = getString("id")!!
                val first_name = getString("first_name")!!
                val last_name = getString("last_name")!!
                val email = getString("email")!!
                val location = getString("location")!!
                return User(id, first_name, last_name, email, location)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting user", e)
                return null
            }
        }
        private const val TAG = "User"
    }
}