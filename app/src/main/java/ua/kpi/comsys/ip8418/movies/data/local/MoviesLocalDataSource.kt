package ua.kpi.comsys.ip8418.movies.data.local

import ua.kpi.comsys.ip8418.movies.data.Movie
import ua.kpi.comsys.ip8418.movies.data.MovieInfo
import ua.kpi.comsys.ip8418.movies.data.MoviesDataSource

class MoviesLocalDataSource(private val db: MovieDatabase) : MoviesDataSource {
    override suspend fun getMovies(search: String) = db.moviesDao().getMovies(search)

    override suspend fun getMovieInfo(i: String): MovieInfo {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        db.moviesDao().save(movies)
    }
}