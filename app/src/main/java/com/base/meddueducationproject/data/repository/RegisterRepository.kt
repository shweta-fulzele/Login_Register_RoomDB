package com.base.meddueducationproject.data.repository

import androidx.lifecycle.LiveData
import com.base.meddueducationproject.data.roomdb.RegisterDatabaseDao
import com.base.meddueducationproject.data.roomdb.RegisterEntity

class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    fun getUser(email: String, password: String): LiveData<List<RegisterEntity>> {
        return dao.getUser(email, password)
    }

    fun getUserByEmail(email: String): LiveData<List<RegisterEntity>> {
        return dao.getUserByEmail(email)
    }

}