package cz.levinzonr.spotistats.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.levinzonr.spotistats.database.dao.CategoryDao
import cz.levinzonr.spotistats.database.dao.ProductDao
import cz.levinzonr.spotistats.database.models.CategoryEntity
import cz.levinzonr.spotistats.database.models.ProductEntity


@Database(entities = [CategoryEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao() : CategoryDao
    abstract fun productDao() : ProductDao
}