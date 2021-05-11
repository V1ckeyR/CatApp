package ua.kpi.comsys.ip8418.images.data.remote

import ua.kpi.comsys.ip8418.images.data.Hits
import ua.kpi.comsys.ip8418.images.data.ImagesDataSource

class ImagesRemoteDataSource(private val api: ImagesApi) : ImagesDataSource {
    override suspend fun getImages(): Hits = api.getImages()
    override suspend fun saveImages(hits: Hits) {
        TODO("Not yet implemented")
    }
}