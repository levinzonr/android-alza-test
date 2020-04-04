package cz.levinzonr.spotistats.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import cz.levinzonr.spotistats.database.converters.StringListConverter
import cz.levinzonr.spotistats.database.dao.CategoryDao
import cz.levinzonr.spotistats.database.dao.ProductDao
import cz.levinzonr.spotistats.database.models.CategoryEntity
import cz.levinzonr.spotistats.database.models.ProductEntity


@Database(entities = [CategoryEntity::class, ProductEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoriesDao() : CategoryDao
    abstract fun productDao() : ProductDao
}