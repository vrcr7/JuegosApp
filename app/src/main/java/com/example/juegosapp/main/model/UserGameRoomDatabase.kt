package com.example.juegosapp.main.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserGame::class), version = 1, exportSchema = false)
public abstract class UserGameRoomDatabase: RoomDatabase(){
    abstract fun userGameDao(): UserGameDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserGameRoomDatabase? = null

        fun getDatabase(context: Context): UserGameRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserGameRoomDatabase::class.java,
                    "usergame_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}