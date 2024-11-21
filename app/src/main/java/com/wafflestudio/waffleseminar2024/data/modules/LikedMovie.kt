package com.wafflestudio.waffleseminar2024.data.modules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_movies")
data class LikedMovie(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "poster_path") val posterPath: String
)