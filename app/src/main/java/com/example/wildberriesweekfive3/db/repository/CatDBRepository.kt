package com.example.wildberriesweekfive3.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.wildberriesweekfive3.db.CatDao
import com.example.wildberriesweekfive3.db.CatModelLikeDB


class CatDBRepository(private val catDao: CatDao) {
    val catsLike: LiveData<List<CatModelLikeDB>> = catDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cat: CatModelLikeDB) {
        catDao.insert(cat)
    }
}



