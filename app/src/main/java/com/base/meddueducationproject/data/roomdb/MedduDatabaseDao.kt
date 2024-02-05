package com.base.meddueducationproject.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MedduDatabaseDao {

    @Insert
    suspend fun insert(entity: MedduEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY user_id ASC")
    fun getAllUsers(): LiveData<List<MedduEntity>>

    @Query("SELECT * FROM Register_users_table WHERE email LIKE :email AND password LIKE :password")
    fun getUser(email: String, password: String): LiveData<List<MedduEntity>>

    @Query("SELECT * FROM Register_users_table WHERE email LIKE :email")
    fun getUserByEmail(email: String): LiveData<List<MedduEntity>>

    @Query("SELECT * FROM Register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): MedduEntity?
}