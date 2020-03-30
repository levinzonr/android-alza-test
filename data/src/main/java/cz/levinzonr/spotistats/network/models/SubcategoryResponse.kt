package cz.levinzonr.spotistats.network.models

data class SubcategoryResponse(
        val cat_type: String,
        val cat_type_id: Int,
        val child_cnt: Int,
        val children: Any,
        val id: Int,
        val img: String,
        val localTitlePageLink: LinkResponse?,
        val ltp: Boolean,
        val name: String,
        val order: Int,
        val producer: Int,
        val show_as_tiles: Boolean,
        val type: String,
        val url: String
)