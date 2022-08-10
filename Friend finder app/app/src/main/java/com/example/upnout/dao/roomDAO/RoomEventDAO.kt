package com.example.upnout.dao.roomDAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.upnout.models.EventRoom


@Dao
interface RoomEventDAO {
    @Query("SELECT * FROM event_table")
    fun getEvents(): LiveData<List<EventRoom>>

    @Query("SELECT * FROM event_table WHERE title = :title AND organizer = :organizer")
    fun getEvent(title: String, organizer: String): EventRoom

    //Will be used by the database to refresh the local storage
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg events: EventRoom)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvent(event: EventRoom)

    @Delete
    fun delete(event: EventRoom)
}