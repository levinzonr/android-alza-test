package cz.levinzonr.spotistats.domain.repository

import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail

interface ProductRepository {
    suspend fun getProductsFromCategory(categoryId: String) : List<Product>
    suspend fun getProductDetails(id: String) : Product
}