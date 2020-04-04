package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.cache.CachedItemListStrategy
import cz.levinzonr.spotistats.cache.CachingConfiguration
import cz.levinzonr.spotistats.database.dao.CategoryDao
import cz.levinzonr.spotistats.database.models.CategoryEntity
import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.domain.repository.CategoryRepository
import cz.levinzonr.spotistats.domain.repository.RepositoryException
import cz.levinzonr.spotistats.network.Api
import cz.levinzonr.spotistats.network.models.CategoryResponse

class CategoryRepositoryImpl(
        private val remoteDataSource: Api,
        private val localDataSource: CategoryDao,
        private val configuration: CachingConfiguration
) : CategoryRepository {

    override suspend fun getProductCategories(): List<Category> {
       return CachedItemListStrategy<CategoryEntity>(configuration)
               .setCachingSource { localDataSource.findAll() }
               .setRemoteSource { remoteDataSource.getProductCategoriesAsync().getDataOrThrow().map { it.toEntity() } }
               .setOnUpdateItems { localDataSource.insertAll(it) }
               .apply()
               .map { it.toDomain() }
    }


    private fun CategoryResponse.toEntity() : CategoryEntity {
        return CategoryEntity(
                id = id.toString(),
                name = name,
                imageUrl = img
        )
    }
}