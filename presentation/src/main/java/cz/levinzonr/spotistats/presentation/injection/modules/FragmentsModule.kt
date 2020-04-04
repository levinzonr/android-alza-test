package cz.levinzonr.spotistats.presentation.injection.modules

import cz.levinzonr.spotistats.presentation.screens.main.categories.CategoriesFragment
import cz.levinzonr.spotistats.presentation.screens.main.productdetails.ProductDetailsFragment
import cz.levinzonr.spotistats.presentation.screens.main.products.ProductsFragment
import cz.levinzonr.spotistats.presentation.screens.onboarding.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal interface FragmentsModule {

    @ContributesAndroidInjector
    fun contributeCategoryFragments(): CategoriesFragment

    @ContributesAndroidInjector
    fun contributeProductDetailsFragment(): ProductDetailsFragment

    @ContributesAndroidInjector
    fun contributeProductsFragment(): ProductsFragment

    @ContributesAndroidInjector
    fun contributeSplashFragment(): SplashFragment
}