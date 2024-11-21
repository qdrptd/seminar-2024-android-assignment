package com.wafflestudio.waffleseminar2024.data.modules
import androidx.lifecycle.LiveData
import com.wafflestudio.waffleseminar2024.data.modules.APIModules.ApiClient
import com.wafflestudio.waffleseminar2024.data.modules.DBModules.MovieDao
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val movieDao: MovieDao
) {
    val allLikedMovies: LiveData<List<LikedMovie>> = movieDao.getAllLikedMovies()

    suspend fun getMovieById(id: Int): MyEntity {
        return apiClient.getMyEntityById(id)
    }

    suspend fun getMoviesByTitle(titleWord: String): List<MyEntity> {
        return apiClient.getMoviesByTitle(titleWord).results
    }

    suspend fun getMoviesByGenre(genreId: Int): List<MyEntity> {
        return apiClient.getMoviesByGenre(genreId.toString()).results
    }

    suspend fun insertLikedMovie(movie: LikedMovie) {
        movieDao.insertLikedMovie(movie)
    }

    suspend fun isMovieLiked(id: Int): Boolean {
        return movieDao.getLikedMovieById(id) != null
    }

    suspend fun deleteLikedMovie(id: Int) {
        movieDao.deleteLikedMovie(id)
    }
}
