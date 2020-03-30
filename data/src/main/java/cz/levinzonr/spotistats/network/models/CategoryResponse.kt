package cz.levinzonr.spotistats.network.models

import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.domain.models.DataModel

data class CategoryResponse(
        val cat_type: String,
        val cat_type_id: Int,
        val child_cnt: Int,
        val children: List<SubcategoryResponse>,
        val id: Int,
        val img: String,
        val localTitlePageLink: LinkResponse,
        val ltp: Boolean,
        val name: String,
        val order: Int,
        val producer: Int,
        val show_as_tiles: Boolean,
        val type: String,
        val url: String
) : DataModel<Category> {

    override fun toDomain(): Category {
        return Category(
                id = id.toString(),
                name = name,
                imageUrl = img
        )
    }
}