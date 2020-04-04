package cz.levinzonr.spotistats.injection

import android.content.Context
import androidx.room.Room
import cz.levinzonr.spotistats.cache.CachingConfiguration
import cz.levinzonr.spotistats.database.AppDatabase
import cz.levinzonr.spotistats.database.dao.CategoryDao
import cz.levinzonr.spotistats.database.dao.ProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) : AppDatabase{
       return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    @Singleton
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }


    @Provides
    @Singleton
    fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao {
        return appDatabase.categoriesDao()
    }


    @Provides
    fun provideCacheConfiguration() : CachingConfiguration {
        return CachingConfiguration(
                remoteFallback = CachingConfiguration.RemoteFallback.RETURN_CACHE,
                cacheValidityTime = 60_000
        )
    }

}