package com.wafflestudio.waffleseminar2024.data.database

import android.util.Log
import com.wafflestudio.waffleseminar2024.BuildConfig
import javax.inject.Inject


class MovieRepository @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getMovieById(id: Int): MyEntity {
        return apiClient.getMyEntityById(id)
    }

    suspend fun getMoviesByTitle(titleWord: String): List<MyEntity> {
        return apiClient.getMoviesByTitle(titleWord).results
    }

    suspend fun getMoviesByGenre(genreId: Int): List<MyEntity> {
        return apiClient.getMoviesByGenre(genreId.toString()).results
    }
}
