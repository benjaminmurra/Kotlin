package com.example.upnout.dao.firestoreDAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.upnout.models.UserRoom

@Dao
interface FireStoreUserDao {

    @Query("SELECT * FROM user_table")
    fun getUsers(): LiveData<List<UserRoom>>

    @Query("SELECT * FROM user_table WHERE first_name = :firstname AND last_name = :lastname")
    fun getUser(firstname: String, lastname: String): UserRoom

    //Will be used by the database to refresh the local storage
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserRoom)

    @Delete
    fun delete(user: UserRoom)
}