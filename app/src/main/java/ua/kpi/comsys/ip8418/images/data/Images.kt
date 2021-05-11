package ua.kpi.comsys.ip8418.images.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Image(
    @PrimaryKey val id: Int,
    val webformatURL: String
    )

@Serializable
data class Hits(val hits: List<Image>)
