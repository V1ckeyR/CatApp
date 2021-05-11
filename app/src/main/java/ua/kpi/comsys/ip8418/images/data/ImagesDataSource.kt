package ua.kpi.comsys.ip8418.images.data

interface ImagesDataSource {
    suspend fun getImages(): Hits
    suspend fun saveImages(hits: Hits)
}