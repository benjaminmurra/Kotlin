package com.example.upnout.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserRoom(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "first_name") val first_name: String?,
        @ColumnInfo(name = "last_name") val last_name: String?,
        @ColumnInfo(name = "email") val email: String?,
        @ColumnInfo(name = "location") val location: String?,
        @ColumnInfo(name = "profile_completed") val profile_completed: Int?
)
