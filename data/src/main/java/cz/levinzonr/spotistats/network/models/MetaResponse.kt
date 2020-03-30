package cz.levinzonr.spotistats.network.models

data class MetaResponse(
    val href: String,
    val method: Any,
    val rel: List<String>
)