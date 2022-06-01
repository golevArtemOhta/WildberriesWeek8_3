package com.example.wildberriesweekfive3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

open class CatsViewModel : ViewModel() {

    val itemCat = MutableLiveData<List<CatJSONItem>>()
    val itemFavoriteCatJSON = MutableLiveData<List<FavoriteCatJSON>>()
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

    fun like(imageId: String){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            client.post("$url/votes"){
                contentType(ContentType.Application.Json)
                setBody(FavoriteCatJSON(image_id = imageId, value = 1))
            }.body()
        }
    }

    fun getFavoriteCats(){
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val favoriteCats = client.get("$url/votes") {
                parameter("sub_id", "golev")
                parameter("value", 1)
            }.body<List<FavoriteCatJSON>>()
            itemFavoriteCatJSON.postValue(favoriteCats)
        }

    }

}