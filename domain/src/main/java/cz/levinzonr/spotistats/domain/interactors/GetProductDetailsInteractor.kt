package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.ProductDetail
import cz.levinzonr.spotistats.domain.repository.ProductRepository

class GetProductDetailsInteractor(
        private val repository: ProductRepository
): Interactor<String, ProductDetail> {
    override suspend fun invoke(input: String): ProductDetail {
        return repository.getProductDetails(input)
    }
}