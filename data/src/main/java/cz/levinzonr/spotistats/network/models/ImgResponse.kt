package cz.levinzonr.spotistats.network.models

data class ImgResponse(
    val big_url: String,
    val id: String,
    val order: Int,
    val origUrl: String,
    val url: String
)