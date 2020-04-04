package cz.levinzonr.spotistats.injection.components

import cz.levinzonr.spotistats.App
import cz.levinzonr.spotistats.AppConfigImpl
import cz.levinzonr.spotistats.injection.DataModule
import cz.levinzonr.spotistats.injection.modules.AppConfigModule
import cz.levinzonr.spotistats.injection.modules.AppModule
import cz.levinzonr.spotistats.presentation.injection.PresentationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
        modules = [
            AppConfigModule::class,
            AndroidSupportInjectionModule::class,
            AppModule::class,
            DataModule::class,
            PresentationModule::class
        ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}