package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail
import cz.levinzonr.spotistats.domain.repository.ProductRepository

class GetProductByIdInteractor(
        private val repository: ProductRepository
): Interactor<String, Product> {
    override suspend fun invoke(input: String): Product {
        return repository.getProductDetails(input)
    }
}