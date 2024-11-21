package com.wafflestudio.waffleseminar2024.data.modules.APIModules

import com.squareup.moshi.JsonClass
import com.wafflestudio.waffleseminar2024.data.modules.MyEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JsonClass(generateAdapter = true)
data class ResponseWrapper<T>(
    val page: Int,
    val results: List<T>,
    val total_pages: Int,
    val total_results: Int
    )


interface ApiClient {

    @GET("3/search/movie")
    suspend fun getMoviesByTitle(
        @Query("query") query: String
    ): ResponseWrapper<MyEntity>


    @GET("3/discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") query: String
    ): ResponseWrapper<MyEntity>


    @GET("3/movie/{movie_id}")
    suspend fun getMyEntityById(
        @Path("movie_id") movieId: Int
    ): MyEntity

}