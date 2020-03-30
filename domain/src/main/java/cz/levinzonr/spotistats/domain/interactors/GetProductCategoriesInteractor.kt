package cz.levinzonr.spotistats.domain.interactors

import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.domain.repository.CategoryRepository

class GetProductCategoriesInteractor(
        private val repository: CategoryRepository
): NoInputInteractor<List<Category>> {
    override suspend fun invoke(input: Unit): List<Category> {
        return repository.getProductCategories()
    }
}