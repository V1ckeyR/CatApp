package ua.kpi.comsys.ip8418.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi

class ImagesViewModel : ViewModel() {
    var images = MutableLiveData<Hits>()

    @ExperimentalSerializationApi
    private val repository = ImagesRepository(ImagesRemoteDataSource(getImageApi()))

    @ExperimentalSerializationApi
    fun loadImages() {
        viewModelScope.launch { images.postValue(repository.getImages()) }
    }


}