package com.example.upnout.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.upnout.dao.firestoreDAO.FireStoreEventDao
import com.example.upnout.database.LocalDatabase
import com.example.upnout.objects.Event
import com.example.upnout.repositories.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application)  {
    private val eventRepository: EventRepository
    val eventsLiveData : LiveData<List<Event>>

    init {
        val eventsDao = LocalDatabase.getDatabase(application).eventDao()
        val fireStoreEventDao = FireStoreEventDao()
        eventRepository = EventRepository(eventsDao, fireStoreEventDao)
        eventsLiveData = eventRepository.liveevents
    }

    fun insertEvent(event : Event) = viewModelScope.launch(Dispatchers.IO) {
        eventRepository.insert(event)
    }
}