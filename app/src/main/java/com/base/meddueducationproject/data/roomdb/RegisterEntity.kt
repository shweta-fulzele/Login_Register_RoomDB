package com.base.meddueducationproject.data.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register_users_table")
data class RegisterEntity(

    @ColumnInfo(name = "user_id", typeAffinity = ColumnInfo.INTEGER)
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "user_name", typeAffinity = ColumnInfo.TEXT)
    var userName: String,

    @ColumnInfo(name = "mobile", typeAffinity = ColumnInfo.TEXT)
    var mobile: String,

    @ColumnInfo(name = "email", typeAffinity = ColumnInfo.TEXT)
    var email: String,

    @ColumnInfo(name = "password", typeAffinity = ColumnInfo.TEXT)
    var password: String

)