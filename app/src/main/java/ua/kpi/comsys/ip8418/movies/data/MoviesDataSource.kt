package ua.kpi.comsys.ip8418.movies.data

interface MoviesDataSource {
    suspend fun getMovies(search: String): List<Movie>
    suspend fun getMovieInfo(i: String): MovieInfo
    suspend fun saveMovies(movies: List<Movie>)
}