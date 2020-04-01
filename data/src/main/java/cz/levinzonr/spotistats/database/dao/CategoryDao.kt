package cz.levinzonr.spotistats.database.dao

import androidx.room.Dao
import androidx.room.Query
import cz.levinzonr.spotistats.database.models.CategoryEntity

@Dao
interface CategoryDao: BaseDao<CategoryEntity> {
    @Query("SELECT * FROM CategoryEntity")
    fun findAll() : List<CategoryEntity>
}