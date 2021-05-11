package ua.kpi.comsys.ip8418.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8418.movies.data.Movie
import ua.kpi.comsys.ip8418.movies.data.MovieInfo
import ua.kpi.comsys.ip8418.movies.data.MoviesRepository

@ExperimentalSerializationApi
class MovieViewModel : ViewModel() {
    var movies = MutableLiveData<List<Movie>>()
    var error = MutableLiveData<String>()
    var movieInfo = MutableLiveData<MovieInfo>()
    var MIError = MutableLiveData<String>()
    var searchText = ""

    lateinit var repository: MoviesRepository

    fun loadMovies(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movies.postValue(repository.getMovies(search))
            } catch (e: Exception) {
                error.postValue(e.message)
            }
        }
    }

    fun loadMovieInfo(i: String) {
        viewModelScope.launch {
            try {
                movieInfo.postValue(repository.getMovieInfo(i))
            } catch (e: Exception) {
                MIError.postValue(e.message)
            }
        }
    }
}