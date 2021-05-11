package ua.kpi.comsys.ip8418.images.data.local

import ua.kpi.comsys.ip8418.images.data.Hits
import ua.kpi.comsys.ip8418.images.data.ImagesDataSource

class ImagesLocalDataSource(private val db : ImageDatabase) : ImagesDataSource {
    override suspend fun getImages() = Hits(db.imagesDao().getImages())

    override suspend fun saveImages(hits: Hits) {
        db.imagesDao().save(hits.hits)
    }
}