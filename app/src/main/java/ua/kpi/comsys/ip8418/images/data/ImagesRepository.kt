package ua.kpi.comsys.ip8418.images.data

class ImagesRepository (private val remote: ImagesDataSource,
                        private val local: ImagesDataSource) {
    suspend fun getImagesRemote() : Hits {
        try {
            val data = remote.getImages()
            local.saveImages(data)
        } finally {
            return local.getImages()
        }
    }
}