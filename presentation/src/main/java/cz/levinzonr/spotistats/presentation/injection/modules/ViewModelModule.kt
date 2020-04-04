package cz.levinzonr.spotistats.presentation.injection.modules

import androidx.lifecycle.ViewModel
import cz.levinzonr.spotistats.presentation.injection.factory.ViewModelKey
import cz.levinzonr.spotistats.presentation.screens.main.categories.CategoriesViewModel
import cz.levinzonr.spotistats.presentation.screens.main.productdetails.ProductDetailsViewModel
import cz.levinzonr.spotistats.presentation.screens.main.products.ProductsViewModel
import cz.levinzonr.spotistats.presentation.screens.onboarding.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    fun bindProductDetailsViewModel(viewModel: ProductDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    fun bindCategoriesViewModel(viewModel: CategoriesViewModel): ViewModel

}