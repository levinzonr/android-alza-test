package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail
import cz.levinzonr.spotistats.domain.repository.ProductRepository
import cz.levinzonr.spotistats.network.Api
import cz.levinzonr.spotistats.network.models.ProductsFilterParams

class ProductRepositoryImpl(
        private val reomoteDataSource: Api
) : ProductRepository {
    override suspend fun getProductsFromCategory(categoryId: String): List<Product> {
        val filter = ProductsFilterParams(categoryId)
        return listOf()
    }

    override suspend fun getProductDetails(id: String): ProductDetail {
        return reomoteDataSource.getProductDetailsAsync(id).data.toDomain()
    }
}