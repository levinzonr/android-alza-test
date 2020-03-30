package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.domain.repository.CategoryRepository
import cz.levinzonr.spotistats.network.Api

class CategoryRepositoryImpl(
        private val remoteDataSource: Api
) : CategoryRepository {

    override suspend fun getProductCategories(): List<Category> {
        return remoteDataSource.getProductCategoriesAsync().data.map { it.toDomain() }
    }
}