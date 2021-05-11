package ua.kpi.comsys.ip8418.movies.data

class MoviesRepository(private val remote: MoviesDataSource,
                       private val local: MoviesDataSource
) {
    suspend fun getMovies(s: String): List<Movie> {
        try {
            val data = remote.getMovies(s)
            local.saveMovies(data)
        } finally {
            return local.getMovies(s)
        }
    }

    suspend fun getMovieInfo(i: String) = remote.getMovieInfo(i)
}