package cz.levinzonr.spotistats.domain.models

data class ProductDetail(
        val id: String,
        val name: String,
        val description: String,
        val mainImageUrl: String?,
        val imagesUrls: List<String>,
        val advertisingMessages: List<String>,
        val rating: Double,
        val reliabilityPercentage: Double,
        val reliabilityMessage: String
) : DomainModel