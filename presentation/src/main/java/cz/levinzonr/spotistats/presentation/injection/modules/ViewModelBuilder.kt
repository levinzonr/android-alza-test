package cz.levinzonr.spotistats.presentation.injection.modules

import androidx.lifecycle.ViewModelProvider
import cz.levinzonr.spotistats.presentation.injection.factory.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal interface ViewModelBuilder {

    @Binds
    fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}