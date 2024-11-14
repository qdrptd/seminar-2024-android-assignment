package com.wafflestudio.waffleseminar2024.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyEntity(entity: com.wafflestudio.waffleseminar2024.data.database.MyEntity)

    @Query("SELECT * FROM example_table2")
    fun getAllMyEntities(): List<com.wafflestudio.waffleseminar2024.data.database.MyEntity>

    @Query("SELECT * FROM example_table2 WHERE id = :id")
    fun getMyEntityById(id:Int): com.wafflestudio.waffleseminar2024.data.database.MyEntity

    @Query("SELECT * FROM example_table2 WHERE title LIKE '%' || :titleWord || '%'")
    fun getMoviesByTitle(titleWord: String): List<com.wafflestudio.waffleseminar2024.data.database.MyEntity>

    @Query("SELECT * FROM example_table2 WHERE genres LIKE '%' || :genreId || '%'")
    fun getMoviesByGenre(genreId: Int): List<com.wafflestudio.waffleseminar2024.data.database.MyEntity>
}