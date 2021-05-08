package ua.kpi.comsys.ip8418.images

interface ImagesDataSource {
    suspend fun getImages(): Hits
}