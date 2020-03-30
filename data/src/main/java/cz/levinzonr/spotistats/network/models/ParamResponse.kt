package cz.levinzonr.spotistats.network.models

data class ParamResponse(
        val id: Int,
        val name: String,
        val values: List<ValueResponse>,
        val values_cnt: Int
)