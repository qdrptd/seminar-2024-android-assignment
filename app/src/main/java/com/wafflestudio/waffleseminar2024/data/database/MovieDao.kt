package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyEntity(entity: MyEntity)

    @Query("SELECT * FROM example_table2")
    fun getAllMyEntities(): List<MyEntity>

    @Query("SELECT * FROM example_table2 WHERE id = :id")
    fun getMyEntityById(id:Int): MyEntity

    @Query("SELECT * FROM example_table2 WHERE title LIKE '%' || :titleWord || '%'")
    fun getMoviesByTitle(titleWord: String): List<MyEntity>

    @Query("SELECT * FROM example_table2 WHERE genres LIKE '%' || :genreId || '%'")
    fun getMoviesByGenre(genreId: Int): List<MyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLikedMovie(likedMovie: LikedMovie)

    @Query("SELECT * FROM liked_movies WHERE id = :movieId")
    fun getLikedMovieById(movieId: Int): LikedMovie?

    @Query("DELETE FROM liked_movies WHERE id = :movieId")
    fun deleteLikedMovie(movieId: Int)

    @Query("SELECT * FROM liked_movies")
    fun getAllLikedMovies(): List<LikedMovie>
}