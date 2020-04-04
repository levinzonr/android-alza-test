package cz.levinzonr.spotistats.injection.modules

import cz.levinzonr.spotistats.AppConfigImpl
import cz.levinzonr.spotistats.domain.AppConfig
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppConfigModule {

    @Binds
    @Singleton
    fun bindAppConfig(appConfigImpl: AppConfigImpl) : AppConfig
}