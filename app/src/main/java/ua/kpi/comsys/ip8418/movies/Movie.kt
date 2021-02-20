package ua.kpi.comsys.ip8418.movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
        @SerialName("Title")
        val title: String,
        @SerialName("Year")
        val year: String,
        @SerialName("imdbID")
        val imdbID: String,
        @SerialName("Type")
        val type: String,
        @SerialName("Poster")
        val poster: String
)

@Serializable
data class Search(
        @SerialName("Search")
        val movies: List<Movie>
)