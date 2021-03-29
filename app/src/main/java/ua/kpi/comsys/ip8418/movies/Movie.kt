package ua.kpi.comsys.ip8418.movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
        @SerialName("Title")
        val title: String,
        @SerialName("Year")
        val year: String,
        @SerialName("Type")
        val type: String,
        @SerialName("imdbID")
        val imdbID: String? = null,
        @SerialName("Poster")
        val poster: String? = null
)

@Serializable
data class Search(
        @SerialName("Search")
        val movies: List<Movie>
)

@Serializable
data class MovieInfo(
        @SerialName("Title")
        val title: String?,
        @SerialName("Year")
        val year: String?,
        @SerialName("Poster")
        val poster: String?,
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