package com.base.meddueducationproject.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(entity: RegisterEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY user_name DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM Register_users_table WHERE email = :email AND password = :password")
    fun getUser(email: String, password: String): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM Register_users_table WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<List<RegisterEntity>>
}