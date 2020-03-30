package cz.levinzonr.spotistats.network.models

data class VideoResponse(
    val id: Int,
    val order: Int,
    val url: String,
    val ythash: String
)