package com.wafflestudio.waffleseminar2024.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.data.database.LikedMovie
import com.wafflestudio.waffleseminar2024.data.database.MovieRepository
import com.wafflestudio.waffleseminar2024.data.database.MyEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _myEntity = MutableLiveData<MyEntity>()
    val myEntity: LiveData<MyEntity> get() = _myEntity

    val isLiked = MediatorLiveData<Boolean>().apply {
        addSource(myEntity) { entity ->
            value = entity?.id?.let { id ->
                repository.allLikedMovies.value?.any { it.id == id } == true
            } ?: false
        }
        addSource(repository.allLikedMovies) { likedMovies ->
            value = myEntity.value?.id?.let { id ->
                likedMovies.any { it.id == id }
            } ?: false
        }
    }
    private val _searchResults = MutableLiveData<List<Movie>>()
    val searchResults: MutableLiveData<List<Movie>> get() = _searchResults

    val allLikedMovies: LiveData<List<LikedMovie>> = repository.allLikedMovies


    private val _likedMovie = MutableLiveData<MyEntity>()
    private val likedMovie: MutableLiveData<MyEntity> get() = _likedMovie

    fun handleLikeButtonClick(id: Int?, posterPath: String?){
        if(id == null || posterPath == null) return

        viewModelScope.launch{
            val isLiked = repository.isMovieLiked(id)
            Log.d("isLiked:", isLiked.toString())
            if(isLiked){
                repository.deleteLikedMovie(id)
            }
            else{
                repository.insertLikedMovie(LikedMovie(id, posterPath))
            }
        }
    }

    fun fetchLikedMovieDetails(id: Int){
        viewModelScope.launch {
            val movieDetails = withContext(Dispatchers.IO) {
                repository.getMovieById(id)
            }
            _likedMovie.value = movieDetails
        }
    }

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            val movieDetails = withContext(Dispatchers.IO) {
                repository.getMovieById(id)
            }
            _myEntity.value = movieDetails
        }
    }

    fun titleQuery(titleWord: String) {
        viewModelScope.launch {
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByTitle(titleWord).map { entity ->
                    Movie(
                        id = entity.id ?: 0,
                        title = entity.title ?: "",
                        original_title = entity.original_title ?: "",
                        backdrop_path = entity.backdrop_path ?: "",
                        overview = entity.overview ?: "",
                        poster_path = entity.poster_path ?: "",
                        release_date = entity.release_date ?: "",
                        vote_average = entity.vote_average ?: 0.0,
                        runtime = entity.runtime,
                        status = entity.status ?: "",
                        genres = entity.genres,
                        budget = entity.budget ?: 0,
                        revenue = entity.revenue ?: 0
                    )
                }
            }
            _searchResults.value = movies  // 검색 결과 업데이트
        }
    }

    fun genreQuery(genreId: Int) {
        viewModelScope.launch {
            // IO 스레드에서 데이터베이스 작업 수행
            val movies = withContext(Dispatchers.IO) {
                repository.getMoviesByGenre(genreId).map { entity ->
                    Movie(
                        id = entity.id ?: 0,
                        title = entity.title ?: "",
                        original_title = entity.original_title ?: "",
                        backdrop_path = entity.backdrop_path ?: "",
                        overview = entity.overview ?: "",
                        poster_path = entity.poster_path ?: "",
                        release_date = entity.release_date ?: "",
                        vote_average = entity.vote_average ?: 0.0,
                        runtime = entity.runtime,
                        status = entity.status ?: "",
                        genres = entity.genres,
                        budget = entity.budget ?: 0,
                        revenue = entity.revenue ?: 0
                    )
                }
            }
            _searchResults.value = movies  // 검색 결과 업데이트
        }
    }
}