package cz.levinzonr.spotistats.repository

import cz.levinzonr.spotistats.cache.CachedItemListStrategy
import cz.levinzonr.spotistats.cache.CachedItemStrategy
import cz.levinzonr.spotistats.cache.CachingConfiguration
import cz.levinzonr.spotistats.cache.CachingStrategy
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
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class ProductRepositoryImpl(
        private val reomoteDataSource: Api,
        private val localDataSource: ProductDao,
        private val cacheConfiguration: CachingConfiguration
) : ProductRepository {
    override suspend fun getProductsFromCategory(categoryId: String): List<Product> {
        val filterParameters = FilterParams(FilterParameters(categoryId))
        return CachedItemListStrategy<ProductEntity>(cacheConfiguration)
                .setCachingSource { localDataSource.findProductsFromCategory(categoryId) }
                .setOnUpdateItems { localDataSource.insertAll(it) }
                .setRemoteSource { reomoteDataSource.getProductFromCategories(filterParameters).data.map { it.toEntity(categoryId) } }
                .apply()
                .map { it.toDomain() }
    }

    override suspend fun getProductDetails(id: String): Product {
       return ProductDetailsCachingStrategy(cacheConfiguration)
               .setCachingSource { localDataSource.findProductById(id) }
               .setRemoteSource { reomoteDataSource.getProductDetailsAsync(id).data.toEntity() }
               .setOnUpdateItems { localDataSource.insert(it) }
               .apply()
               .toDomain()
    }


    private fun ProductResponse.toEntity(categoryId: String) : ProductEntity {

        return ProductEntity(
                id = id.toString(),
                name = name,
                thumbnailUrl = img,
                price = priceNoCurrency.toDouble(),
                categoryId = categoryId,
                description = "",
                rating = rating,
                advertisements = listOf(advertising),
                reliability = 0.0,
                detailsAvailable = false,
                images = listOf(),
                reliabilityMessage = "",
                availability = "",
                availabilityPostfix = ""
        )
    }


    class ProductDetailsCachingStrategy(
            configuration: CachingConfiguration
    ): CachingStrategy<ProductEntity>(configuration) {
        override fun cacheIsValid(item: ProductEntity?): Boolean {
            return item != null && item.detailsAvailable
        }

        override fun cacheIsValidAndNotExpired(item: ProductEntity?): Boolean {
            return item != null && cacheIsValid(item) && item.isValidNow(cacheValidityTime)
        }
    }

    private fun ProductDetailResponse.toEntity() : ProductEntity {
        return ProductEntity(
                id = id.toString(),
                name = name,
                thumbnailUrl = img,
                price = priceNoCurrency.toDouble(),
                categoryId = categoryId.toString(),
                detailsAvailable = true,
                description = spec,
                rating = rating,
                reliabilityMessage = reliabilityText ?: "",
                reliability = reliability,
                images = imgs.map { it.origUrl },
                advertisements = advertisements,
                availability = avail,
                availabilityPostfix = avail_postfix
        )
    }
}