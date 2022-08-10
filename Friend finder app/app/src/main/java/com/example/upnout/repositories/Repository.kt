package com.example.upnout.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.upnout.dao.firestoreDAO.FireStoreEventDao
import com.example.upnout.dao.firestoreDAO.FireStoreUserDao
import com.example.upnout.dao.roomDAO.RoomEventDAO
import com.example.upnout.dao.roomDAO.RoomUserDao
import com.example.upnout.models.UserRoom

class Repository(private val firestoreEventDAO: FireStoreEventDao, private val firestoreUserDAO: FireStoreUserDao, private val roomUserDao: RoomUserDao, private val roomEventDAO: RoomEventDAO) {

    val users : LiveData<List<UserRoom>> = roomUserDao.getUsers()

    @WorkerThread
    fun refreshData(users: List<UserRoom>){

    }

    @WorkerThread
    fun addUser(user : UserRoom){
        //sers.insertUser(user)
    }
}