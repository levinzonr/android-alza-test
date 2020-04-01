package cz.levinzonr.spotistats.database.dao

import androidx.room.Dao
import androidx.room.Query
import cz.levinzonr.spotistats.database.models.ProductEntity

@Dao
interface ProductDao: BaseDao<ProductEntity> {
    @Query("SELECT * FROM PRODUCTENTITY WHERE id=:id")
    fun findProductById(id: String) : ProductEntity

    @Query("SELECT * FROM ProductEntity WHERE categoryId=:id")
    fun findProductsFromCategory(id: String) : List<ProductEntity>
}