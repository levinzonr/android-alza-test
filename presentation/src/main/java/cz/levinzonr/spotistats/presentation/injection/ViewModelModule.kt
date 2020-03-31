package cz.levinzonr.spotistats.presentation.injection

import cz.levinzonr.spotistats.presentation.screens.main.categories.CategoriesViewModel
import cz.levinzonr.spotistats.presentation.screens.main.productdetails.ProductDetailsViewModel
import cz.levinzonr.spotistats.presentation.screens.main.products.ProductsViewModel
import cz.levinzonr.spotistats.presentation.screens.onboarding.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {

    viewModel { SplashViewModel() }
    viewModel { CategoriesViewModel(get()) }
    viewModel { (id: String) -> ProductsViewModel(id, get()) }
    viewModel { (id: String) -> ProductDetailsViewModel(id, get()) }
}