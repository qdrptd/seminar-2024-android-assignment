package com.wafflestudio.waffleseminar2024.data.database

import android.util.Log


class MovieRepository(private val apiClient: ApiClient) {

    suspend fun getMovieById(id: Int): MyEntity {
        return apiClient.getMyEntityById(id)
    }

    suspend fun getMoviesByTitle(titleWord: String): List<MyEntity> {
        return apiClient.getMoviesByTitle(titleWord).results
    }

    suspend fun getMoviesByGenre(genreId: Int): List<MyEntity> {
        val result = apiClient.getMoviesByGenre(genreId.toString())
        return result.results
    }
}
