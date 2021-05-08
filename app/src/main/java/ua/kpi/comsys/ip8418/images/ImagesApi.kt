package ua.kpi.comsys.ip8418.images

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {
    @GET("/api/")
    suspend fun getImages(@Query("key") apiKey: String = "19193969-87191e5db266905fe8936d565",
                          @Query("q") q: String = "yellow+flowers",
                          @Query("image_type") imageType: String = "photo",
                          @Query("per_page") page: Int = 27): Hits

}

@ExperimentalSerializationApi
fun getImageApi(): ImagesApi {
    val contentType = MediaType.get("application/json")
    val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(contentType))
            .build()
    return retrofit.create(ImagesApi::class.java)
}