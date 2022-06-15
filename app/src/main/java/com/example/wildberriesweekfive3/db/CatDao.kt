package com.example.wildberriesweekfive3.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDao {
    @Query("SELECT * FROM catsLike")
    fun getAll(): LiveData<List<CatModelLikeDB>>

    @Insert
    fun insert(vararg cats: CatModelLikeDB)

    @Delete
    fun delete(cat: CatModelLikeDB)
}