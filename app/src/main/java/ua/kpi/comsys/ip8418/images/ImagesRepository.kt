package ua.kpi.comsys.ip8418.images

class ImagesRepository (private val remote: ImagesDataSource) {
    suspend fun getImages() = remote.getImages()
}