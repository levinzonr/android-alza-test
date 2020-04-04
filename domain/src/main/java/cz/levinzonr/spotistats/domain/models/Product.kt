package cz.levinzonr.spotistats.domain.models

data class Product(
        val id: String,
        val name: String,
        val price: Double,
        val thumbnailUrl: String?,
        val rating: Double
) : DomainModel