package cz.levinzonr.spotistats.injection

import androidx.room.Room
import cz.levinzonr.spotistats.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database").build()
    }
    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().categoriesDao() }
}