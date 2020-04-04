package cz.levinzonr.spotistats.domain.models

import cz.levinzonr.spotistats.domain.extensions.percentOf

data class ProductDetail(
        val description: String,
        val imagesUrls: List<String>,
        val advertisingMessages: List<String>,
        val reliabilityPercentage: Double,
        val reliabilityMessage: String,
        val availability: String,
        val availabilityPostfix: String
)
