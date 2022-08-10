package com.example.upnout.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.upnout.dao.firestoreDAO.FireStoreEventDao
import com.example.upnout.dao.roomDAO.RoomEventDAO
import com.example.upnout.database.FirebaseConnector
import com.example.upnout.models.EventRoom
import com.example.upnout.objects.Event

class EventRepository(private val roomEventDAO: RoomEventDAO, private val fireStoreEventDao: FireStoreEventDao) {
    val fireStoreConnector : FirebaseConnector = FirebaseConnector()

    val liveevents : LiveData<List<Event>> = Transformations.map(roomEventDAO.getEvents()) {
        it.map {
            it.toEvent()
        }
    }

    // The insert function calls the NoteDAO's insertNote method in non-blocking way
    @WorkerThread
    fun insert(event: Event) {
        roomEventDAO.insertEvent(EventRoom.from(event))
    }

    @WorkerThread
    fun getEventFromFireStore(){
        roomEventDAO.insertEvent(fireStoreEventDao.getEvent(fireStoreConnector, "E8N4DAr9FswekM2JBARk"))
    }


}