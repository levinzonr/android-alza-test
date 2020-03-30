package cz.levinzonr.spotistats.network.models

data class RatingResponse(
    val count: Int,
    val id: Int,
    val order: Int,
    val rating: Int
)