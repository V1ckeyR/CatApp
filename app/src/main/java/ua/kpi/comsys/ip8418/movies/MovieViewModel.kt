package ua.kpi.comsys.ip8418.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MovieViewModel : ViewModel() {
    var movies = MutableLiveData<List<Movie>>()
    var error = MutableLiveData<String>()
    var movieInfo = MutableLiveData<MovieInfo>()
    var MIError = MutableLiveData<String>()
    var searchText = ""

    private val repository = MoviesRepository(MoviesRemoteDataSource(getMovieApi()))

    fun loadMovies(search: String) {
        viewModelScope.launch {
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