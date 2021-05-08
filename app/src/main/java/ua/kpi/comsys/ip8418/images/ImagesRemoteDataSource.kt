package ua.kpi.comsys.ip8418.images

class ImagesRemoteDataSource(private val api: ImagesApi) : ImagesDataSource {
    override suspend fun getImages(): Hits = api.getImages()
}