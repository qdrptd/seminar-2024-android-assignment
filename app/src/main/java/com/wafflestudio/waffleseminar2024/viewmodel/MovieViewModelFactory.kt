package com.wafflestudio.waffleseminar2024.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyDatabase.Companion.getDatabase
import com.wafflestudio.waffleseminar2024.data.database.RetrofitInstance

class MovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            val apiService = RetrofitInstance.api
            val repository = MovieRepository(apiService)
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
