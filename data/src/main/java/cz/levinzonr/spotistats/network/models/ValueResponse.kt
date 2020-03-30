package cz.levinzonr.spotistats.network.models

data class ValueResponse(
    val desc: String,
    val id: Int,
    val selected: Boolean,
    val v: Int,
    val visibility: Boolean
)