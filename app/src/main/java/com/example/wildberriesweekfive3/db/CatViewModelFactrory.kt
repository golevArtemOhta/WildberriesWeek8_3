package com.example.wildberriesweekfive3.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wildberriesweekfive3.db.repository.CatDBRepository
import com.example.wildberriesweekfive3.ui.CatsViewModel

class CatViewModelFactrory(private val catDBRepository: CatDBRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatsViewModel(catDBRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

