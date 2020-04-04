package cz.levinzonr.spotistats.injection.modules

import android.content.Context
import cz.levinzonr.spotistats.App
import cz.levinzonr.spotistats.inititializers.AppInitializer
import cz.levinzonr.spotistats.inititializers.AppInitializerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class AppModule {

    @Binds
    abstract fun bindAppInitalizer(initializer: AppInitializerImpl): AppInitializer


    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideContext(application: App): Context = application.applicationContext

        @Provides
        @Named("NAME_BASE_URL")
        fun provideBaseUrlString(): String {
            return cz.levinzonr.spotistats.BuildConfig.API_URL
        }
    }
}