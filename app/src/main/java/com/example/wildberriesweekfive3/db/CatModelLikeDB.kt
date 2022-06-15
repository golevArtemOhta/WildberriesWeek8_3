package com.example.wildberriesweekfive3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "catsLike")
data class CatModelLikeDB(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "url") val url: String
)
