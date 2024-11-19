package com.wafflestudio.waffleseminar2024.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.JsonAdapter
import com.squareup.moshi.JsonClass
import com.wafflestudio.waffleseminar2024.Company
import com.wafflestudio.waffleseminar2024.Country
import com.wafflestudio.waffleseminar2024.Genre
import com.wafflestudio.waffleseminar2024.Language
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
@Entity(tableName = "example_table2")
@TypeConverters(MyConverters::class)
data class MyEntity(
    @PrimaryKey val id: Int?,
    val title: String?,
    val original_title: String?,
    val backdrop_path: String?,
    val budget: Int?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val vote_average: Double?,
    val genres: List<Genre>?,
    val homepage: String?,
    val original_language: String?,
    val popularity: Float?,
    val production_companies: List<Company>?,
    val production_countries: List<Country>?,
    val spoken_languages: List<Language>?,
    val tagline: String?,
    val vote_count: Int?
)


@Serializable
@Parcelize
class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val original_country: String
) : Parcelable


@Serializable
class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)