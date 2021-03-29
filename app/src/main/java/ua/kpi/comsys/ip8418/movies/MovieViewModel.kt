package ua.kpi.comsys.ip8418.movies

import androidx.lifecycle.ViewModel

class MovieViewModel : ViewModel() {
    var movies = mutableListOf<Movie>()
    var movieInfo: MovieInfo? = null
}