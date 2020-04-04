package cz.levinzonr.spotistats

import android.content.Context
import androidx.multidex.MultiDex
import cz.levinzonr.spotistats.inititializers.AppInitializer
import cz.levinzonr.roxie.Roxie
import cz.levinzonr.spotistats.injection.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var initializer: AppInitializer

    override fun onCreate() {
        super.onCreate()
        initializer.init(this)
        Roxie.enableLogging(object : Roxie.Logger {
            override fun log(msg: String) {
                Timber.d(msg)
            }
        })
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}