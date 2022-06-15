package com.example.wildberriesweekfive3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CatModelLikeDB::class], version = 1)
abstract class CatDateBase : RoomDatabase() {
    abstract fun catDao(): CatDao

    companion object {
        @Volatile
        private var INSTANCE: CatDateBase? = null

        fun getDatabase(context: Context): CatDateBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatDateBase::class.java,
                    "cat_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

