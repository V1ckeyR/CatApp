package ua.kpi.comsys.ip8418.movies

class MoviesRepository (private val remote: MoviesDataSource) {
    suspend fun getMovies(s: String) = remote.getMovies(s)
    suspend fun getMovieInfo(i: String) = remote.getMovieInfo(i)
}