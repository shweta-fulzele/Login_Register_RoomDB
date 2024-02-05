package com.base.meddueducationproject.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MedduEntity::class], version = 1, exportSchema = false)
abstract class MedduDatabase : RoomDatabase() {

    abstract val registerDatabaseDao: MedduDatabaseDao
    companion object {

        @Volatile
        private var INSTANCE: MedduDatabase? = null


        fun getInstance(context: Context): MedduDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        MedduDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
