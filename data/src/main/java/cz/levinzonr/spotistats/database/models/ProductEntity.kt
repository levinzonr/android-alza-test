package cz.levinzonr.spotistats.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.levinzonr.spotistats.domain.models.DataModel
import cz.levinzonr.spotistats.domain.models.DomainModel
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail

@Entity
data class ProductEntity(

        @PrimaryKey
        override val id: String,
        val name: String,
        val imgUrl: String,
        val price: Double,
        val categoryId: String,
        val description: String
) : CachedEntity(), DataModel<Product> {

    override fun toDomain(): Product {
        return Product(
                id = id,
                name = name,
                imageUrl = imgUrl,
                price = price
        )
    }

    fun toDetail() : ProductDetail {
        return ProductDetail(
                id = id,
                name = name,
                description = description
        )
    }
}