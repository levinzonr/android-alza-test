package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.database.dao.ProductDao
import cz.levinzonr.spotistats.database.models.ProductEntity
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail
import cz.levinzonr.spotistats.domain.repository.ProductRepository
import cz.levinzonr.spotistats.network.Api
import cz.levinzonr.spotistats.network.models.FilterParameters
import cz.levinzonr.spotistats.network.models.FilterParams
import cz.levinzonr.spotistats.network.models.ProductDetailResponse
import cz.levinzonr.spotistats.network.models.ProductResponse

class ProductRepositoryImpl(
        private val reomoteDataSource: Api,
        private val localDataSource: ProductDao
) : ProductRepository {
    override suspend fun getProductsFromCategory(categoryId: String): List<Product> {
        val filter = FilterParams(FilterParameters(categoryId))
        val response = reomoteDataSource.getProductFromCategories(filter)
        localDataSource.insertAll(response.data.map { it.toEntity(categoryId) })
        return reomoteDataSource.getProductFromCategories(filter).data.map { it.toDomain() }
    }

    override suspend fun getProductDetails(id: String): ProductDetail {
        return reomoteDataSource.getProductDetailsAsync(id).data.also {
            localDataSource.insert(it.toEntity())
        }.toDomain()
    }

    private fun ProductResponse.toEntity(categoryId: String) : ProductEntity {
        return ProductEntity(
                id = id.toString(),
                name = name,
                imgUrl = img,
                price = priceNoCurrency.toDouble(),
                categoryId = categoryId,
                description = ""

        )
    }

    private fun ProductDetailResponse.toEntity() : ProductEntity {
        return ProductEntity(
                id = id.toString(),
                name = name,
                imgUrl = img,
                price = priceNoCurrency.toDouble(),
                categoryId = categoryId.toString(),
                description = ""
        )
    }
}