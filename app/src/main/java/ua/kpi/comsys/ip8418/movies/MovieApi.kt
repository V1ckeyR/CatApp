package ua.kpi.comsys.ip8418.movies

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMovies(@Query("apikey") apiKey: String = "7e9fe69e",
                          @Query("s") request: String,
                          @Query("page") page: Int = 1): MovieResponse

    @GET("/")
    suspend fun getMovieInfo(@Query("apikey") apiKey: String = "7e9fe69e",
                             @Query("i") identifier: String): MovieInfo
}

@ExperimentalSerializationApi
fun getMovieApi(): MovieApi {
    val contentType = MediaType.get("application/json")
    val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
    return retrofit.create(MovieApi::class.java)
}