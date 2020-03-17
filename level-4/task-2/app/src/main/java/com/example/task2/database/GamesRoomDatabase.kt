package com.example.task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.task2.models.DateConverter
import com.example.task2.models.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class GamesRoomDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "games"

        @Volatile
        private var gamesRoomDatabaseInstance: GamesRoomDatabase? = null

        fun getDatabase(context: Context): GamesRoomDatabase? {
            if(gamesRoomDatabaseInstance == null) {
                synchronized(GamesRoomDatabase::class.java) {
                    if(gamesRoomDatabaseInstance == null) {
                        gamesRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, GamesRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }

            return gamesRoomDatabaseInstance
        }
    }
}