package cz.levinzonr.spotistats.network.models

data class ParameterGroup(
        val groupId: Int,
        val hierarchic: Boolean,
        val name: String,
        val params: List<ParamResponse>,
        val params_cnt: Int
)