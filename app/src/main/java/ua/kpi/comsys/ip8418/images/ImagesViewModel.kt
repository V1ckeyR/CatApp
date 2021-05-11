package ua.kpi.comsys.ip8418.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8418.images.data.Hits
import ua.kpi.comsys.ip8418.images.data.ImagesRepository

class ImagesViewModel : ViewModel() {
    var images = MutableLiveData<Hits>()

    @ExperimentalSerializationApi
    lateinit var repository: ImagesRepository

    @ExperimentalSerializationApi
    fun loadImages() {
        viewModelScope.launch(Dispatchers.IO) {
            images.postValue(repository.getImagesRemote())
        }
    }


}