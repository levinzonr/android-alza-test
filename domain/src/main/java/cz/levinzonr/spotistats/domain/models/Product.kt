package cz.levinzonr.spotistats.domain.models

import cz.levinzonr.spotistats.domain.extensions.percentOf

data class Product(
        val id: String,
        val name: String,
        val price: Double,
        val thumbnailUrl: String?,
        val rating: Double,
        val details: ProductDetail?
) : DomainModel {
    val ratingPercentage = rating.percentOf(5)
}