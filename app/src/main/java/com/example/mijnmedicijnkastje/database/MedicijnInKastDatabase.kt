package com.example.mijnmedicijnkastje.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MedicijnInKast::class], version = 2, exportSchema = false)
abstract class MedicijnInKastDatabase : RoomDatabase() {

    abstract val medicijnDatabaseDAO: MedicijnDatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: MedicijnInKastDatabase? = null

        fun getInstance(context: Context): MedicijnInKastDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MedicijnInKastDatabase::class.java,
                        "medicijnkast_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
