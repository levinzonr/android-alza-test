package cz.levinzonr.spotistats.network.models

data class PromoResponse(
    val count: Int,
    val id: Int,
    val img: String,
    val is_enabled: Boolean,
    val name: String,
    val order: Int,
    val price: String
)