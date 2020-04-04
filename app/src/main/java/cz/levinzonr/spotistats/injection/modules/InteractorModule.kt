package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.interactors.GetProductCategoriesInteractor
import cz.levinzonr.spotistats.domain.interactors.GetProductByIdInteractor
import cz.levinzonr.spotistats.domain.interactors.GetProductsFromCategoryInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory { GetProductCategoriesInteractor(get()) }
    factory { GetProductByIdInteractor(get()) }
    factory { GetProductsFromCategoryInteractor(get()) }
}