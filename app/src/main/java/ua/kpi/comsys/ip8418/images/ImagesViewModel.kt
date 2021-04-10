package ua.kpi.comsys.ip8418.images

import android.net.Uri
import androidx.lifecycle.ViewModel

class ImagesViewModel : ViewModel() {
    var images = mutableListOf<Uri>()
}