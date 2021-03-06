package ua.kpi.comsys.ip8418.movies.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Movie(
        @PrimaryKey val imdbID: String,
        @SerialName("Title")
        val title: String,
        @SerialName("Year")
        val year: String,
        @SerialName("Type")
        val type: String,
        @SerialName("Poster")
        val poster: String,
)

@Serializable
data class MovieResponse(
        @SerialName("Response")
        val response: String,
        @SerialName("Search")
        val movies: List<Movie>? = null,
        @SerialName("Error")
        val error: String? = null,
)

@Serializable
data class MovieInfo(
        @SerialName("Response")
        val response: String,
        @SerialName("Error")
        val error: String? = null,
        @SerialName("Title")
        val title: String? = null,
        @SerialName("Year")
        val year: String? = null,
        @SerialName("Poster")
        val poster: String? = null,
        @SerialName("Rated")
        val rated: String? = null,
        @SerialName("Released")
        val released: String? = null,
        @SerialName("Runtime")
        val runtime: String? = null,
        @SerialName("Genre")
        val genre: String? = null,
        @SerialName("Director")
        val director: String? = null,
        @SerialName("Actors")
        val actors: String? = null,
        @SerialName("Language")
        val language: String? = null,
        @SerialName("Country")
        val country: String? = null,
        @SerialName("Awards")
        val awards: String? = null,
        @SerialName("imdbRating")
        val rating: String? = null,
        @SerialName("Production")
        val production: String? = null,
        @SerialName("Plot")
        val plot: String? = null,
)