package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.Category

interface CategoryRepository {
    suspend fun getProductCategories() : List<Category>
}