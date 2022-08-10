package com.example.upnout.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.upnout.objects.Event

@Entity(tableName = "event_table")
data class EventRoom(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "event_description") val event_description: String,
        @ColumnInfo(name = "organizer") val organizer: String,
        @ColumnInfo(name = "location") val location: String,
        //@ColumnInfo(name = "start_time") val start_time: Timestamp?,
        //@ColumnInfo(name = "end_time") val end_time: Timestamp?
) {
        companion object {
                fun from(event: Event): EventRoom {
                        return EventRoom(event.id, event.title, event.event_description, event.organizer, event.location)
                }
        }

        fun toEvent(): Event {
                return Event(id, title, event_description, organizer, location)
        }
}
