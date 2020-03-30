package cz.levinzonr.spotistats.network.models

data class LinkResponse(
    val appLink: String,
    val enabled: Boolean,
    val href: String
)