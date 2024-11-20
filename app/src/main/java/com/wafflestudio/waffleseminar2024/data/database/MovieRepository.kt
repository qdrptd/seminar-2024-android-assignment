package com.wafflestudio.waffleseminar2024.data.database

import android.util.Log
import com.wafflestudio.waffleseminar2024.BuildConfig


class MovieRepository(private val apiClient: ApiClient) {

    suspend fun getMovieById(id: Int): MyEntity {
        return apiClient.getMyEntityById(id)
    }

    suspend fun getMoviesByTitle(titleWord: String): List<MyEntity> {
        return apiClient.getMoviesByTitle(titleWord).results
    }

    suspend fun getMoviesByGenre(genreId: Int): List<MyEntity> {
        Log.d("asdf", BuildConfig.API_KEY.toString())
        Log.d("a", apiClient.getMoviesByGenre(genreId.toString()).results.toString())
        return apiClient.getMoviesByGenre(genreId.toString()).results
    }
}
