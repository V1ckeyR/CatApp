package ua.kpi.comsys.ip8418.images

import kotlinx.serialization.Serializable

@Serializable
data class Image(val webformatURL: String)

@Serializable
data class Hits(val hits: List<Image>)
