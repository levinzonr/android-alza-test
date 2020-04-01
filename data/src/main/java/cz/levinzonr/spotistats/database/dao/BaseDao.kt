package cz.levinzonr.spotistats.database.dao

import androidx.room.*
import cz.levinzonr.spotistats.database.models.CachedEntity

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T) : Long

    @Delete
    fun deleteAll(items: List<T>)

    @Delete
    fun delete(items: List<T>)
}