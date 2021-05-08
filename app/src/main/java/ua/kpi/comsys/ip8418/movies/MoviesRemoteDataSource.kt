package ua.kpi.comsys.ip8418.movies

class MoviesRemoteDataSource(private val api: MovieApi) : MoviesDataSource {
    override suspend fun getMovies(search: String): List<Movie> {
        val result = api.getMovies(request = search)
        if (result.response == "True") {
            return result.movies ?: listOf()
        } else {
            error(result.error ?: "Unknown error")
        }
    }

    override suspend fun getMovieInfo(i: String): MovieInfo {
        val result = api.getMovieInfo(identifier = i)
        if (result.response == "True") {
            return result
        } else {
            error(result.error ?: "Unknown error")
        }
    }
}