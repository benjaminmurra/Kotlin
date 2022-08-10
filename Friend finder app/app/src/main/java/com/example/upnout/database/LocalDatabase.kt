package com.example.upnout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upnout.dao.roomDAO.RoomEventDAO
import com.example.upnout.dao.roomDAO.RoomUserDao
import com.example.upnout.models.EventRoom
import com.example.upnout.models.UserRoom

//Increment the version number by 1 each time you make a change in the database. So if you get a bunch of errors try changing the version number.
//This is like a different application
@Database(entities = [UserRoom::class, EventRoom::class], version = 4)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao() : RoomUserDao
    abstract fun eventDao() : RoomEventDAO

    companion object {
        @Volatile
        private var INSTANCE : LocalDatabase? = null

        fun getDatabase(context: Context) : LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "local_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}