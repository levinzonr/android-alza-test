package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.domain.interactors.GetProductCategoriesInteractor
import cz.levinzonr.spotistats.domain.interactors.GetProductDetailsInteractor
import cz.levinzonr.spotistats.domain.interactors.GetProductsFromCategoryInteractor
import cz.levinzonr.spotistats.domain.interactors.PostsInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory { GetProductCategoriesInteractor(get()) }
    factory { GetProductDetailsInteractor(get()) }
    factory { GetProductsFromCategoryInteractor(get()) }
}