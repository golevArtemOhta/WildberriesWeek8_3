package com.example.wildberriesweekfive3.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wildberriesweekfive3.data.CatJSONItem
import com.example.wildberriesweekfive3.db.repository.CatDBRepository
import com.example.wildberriesweekfive3.db.CatModelLikeDB
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


open class CatsViewModel(private val catDBRepository: CatDBRepository) : ViewModel() {


    val itemCat = MutableLiveData<List<CatJSONItem>>()
    val itemFavoriteCatJSON: LiveData<List<CatModelLikeDB>> = catDBRepository.catsLike

    var favoriteList: MutableList<CatJSONItem> = mutableListOf()
    private var job: Job? = null
    val url: String = "https://api.thecatapi.com/v1/images/search?api_key=cc8368e2-0bfa-4af0-a21a-9d20ecdad446"
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }

    fun request() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val cat= client.get(url).body<List<CatJSONItem>>()
            itemCat.postValue(cat)
        }

    }

    fun like(favoriteCat: CatJSONItem){
        viewModelScope.launch {
            catDBRepository.insert(CatModelLikeDB(favoriteCat.id, favoriteCat.url))
        }
    }


}

