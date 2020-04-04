package cz.levinzonr.spotistats.presentation.injection.modules

import cz.levinzonr.spotistats.presentation.screens.main.MainActivity
import cz.levinzonr.spotistats.presentation.screens.main.categories.CategoriesFragment
import cz.levinzonr.spotistats.presentation.screens.main.productdetails.ProductDetailsFragment
import cz.levinzonr.spotistats.presentation.screens.main.products.ProductsFragment
import cz.levinzonr.spotistats.presentation.screens.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
internal interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeOnboardingActivity(): OnboardingActivity
}