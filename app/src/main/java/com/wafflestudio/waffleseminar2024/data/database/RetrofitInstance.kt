package com.wafflestudio.waffleseminar2024.data.database

import com.google.gson.Gson
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wafflestudio.waffleseminar2024.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

data class ProductionCountriesJSON(
    val iso_3166_1: String,
    val name: String
)

class ProductionCountriesAdapter {
    @FromJson
    fun fromJson(countries: List<ProductionCountriesJSON>?): String? {
        val countryNames = countries?.filterIsInstance<ProductionCountriesJSON>()
            ?.joinToString { it.name ?: "" }
        return countryNames
    }
    @ToJson
    fun toJson(countries: String?): List<Map<String, String>> {
        return emptyList()
    }
}

data class ProductionCompaniesJSON(
    val id: Int,
    val logo_path: String,
    val name: String,
    val original_country: String
)

data class ProductionCompanies(val companies: String)

class ProductionCompaniesAdapter {
    @FromJson
    fun fromJson(companies: List<ProductionCompaniesJSON>?): String? {
        val companyNames = companies?.map { it.name }
            ?.joinToString(", ")

        return companyNames
    }

    @ToJson
    fun toJson(companies: ProductionCompanies): List<ProductionCompaniesJSON> {
        return emptyList()
    }
}


val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object RetrofitInstance {

    private const val BASE_URL = "https://api.themoviedb.org/"
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()


    val api: ApiClient by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiClient::class.java)
    }
}