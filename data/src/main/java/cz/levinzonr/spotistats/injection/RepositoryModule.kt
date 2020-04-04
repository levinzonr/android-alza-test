package cz.levinzonr.spotistats.injection

import cz.levinzonr.spotistats.domain.repository.CategoryRepository
import cz.levinzonr.spotistats.domain.repository.ProductRepository
import cz.levinzonr.spotistats.repository.CategoryRepositoryImpl
import cz.levinzonr.spotistats.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCategoryRepository(repository: CategoryRepositoryImpl) : CategoryRepository

    @Binds
    @Singleton
    fun bindProductRepository(repositoryImpl: ProductRepositoryImpl) : ProductRepository
}