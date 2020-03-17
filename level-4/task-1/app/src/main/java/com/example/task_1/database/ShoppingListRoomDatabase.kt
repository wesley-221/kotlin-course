package com.example.task_1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task_1.models.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ShoppingListRoomDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private const val DATABASE_NAME = "shopping_list"

        @Volatile
        private var shoppingListRoomDatabaseInstance: ShoppingListRoomDatabase? = null

        fun getDatabase(context: Context): ShoppingListRoomDatabase? {
            if(shoppingListRoomDatabaseInstance == null) {
                synchronized(ShoppingListRoomDatabase::class.java) {
                    if(shoppingListRoomDatabaseInstance == null) {
                        shoppingListRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, ShoppingListRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }
            }

            return shoppingListRoomDatabaseInstance
        }
    }
}