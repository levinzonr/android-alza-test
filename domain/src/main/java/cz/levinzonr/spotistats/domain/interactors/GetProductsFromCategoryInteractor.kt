package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsFromCategoryInteractor @Inject constructor(
        private val repository: ProductRepository
) : Interactor<String, List<Product>> {
    override suspend fun invoke(input: String): List<Product> {
        return repository.getProductsFromCategory(input)
    }
}