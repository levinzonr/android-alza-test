package cz.levinzonr.spotistats.network.models

data class ReviewResponse(
    val author: String,
    val date: String,
    val gender: String,
    val id: Int,
    val neg: String,
    val order: Int,
    val pos: String,
    val rating: Int,
    val text: String
)