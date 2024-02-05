package com.base.meddueducationproject.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.base.meddueducationproject.data.roomdb.MedduDatabaseDao
import com.base.meddueducationproject.data.roomdb.MedduEntity

class MedduRepository(private val dao: MedduDatabaseDao) {

    fun getUser(email: String, password: String): LiveData<List<MedduEntity>> {
        return dao.getUser(email, password)
    }

    fun getUserByEmail(email: String): LiveData<List<MedduEntity>> {
        return dao.getUserByEmail(email)
    }

    val users = dao.getAllUsers()
    suspend fun insert(user: MedduEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String):MedduEntity?{
        Log.i("MYTAG", "inside Repository Getusers fun ")
        return dao.getUsername(userName)
    }

}