package com.example.wildberriesweekfive3

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCatJSON(
    val id: Int = 0,
    val image_id: String,
    val sub_id: String = "golev",
    val value: Int = 2
)
