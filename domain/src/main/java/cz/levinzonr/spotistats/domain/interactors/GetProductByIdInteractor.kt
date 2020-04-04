package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail
import cz.levinzonr.spotistats.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdInteractor @Inject constructor(
        private val repository: ProductRepository
): Interactor<String, Product> {
    override suspend fun invoke(input: String): Product {
        return repository.getProductDetails(input)
    }
}