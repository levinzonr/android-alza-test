package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.repository.CategoryRepository
import cz.levinzonr.spotistats.domain.repository.ProductRepository
import cz.levinzonr.spotistats.repository.CategoryRepositoryImpl
import cz.levinzonr.spotistats.repository.ProductRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {

    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }

}