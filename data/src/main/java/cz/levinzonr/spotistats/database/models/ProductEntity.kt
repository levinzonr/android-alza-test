package cz.levinzonr.spotistats.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.levinzonr.spotistats.domain.models.DataModel
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail

@Entity
data class ProductEntity(

        @PrimaryKey
        override val id: String,
        val name: String,
        val thumbnailUrl: String?,
        val price: Double,
        val categoryId: String,
        val detailsAvailable: Boolean,
        val images: List<String>,
        val advertisements: List<String>,
        val description: String?,
        val rating: Double = 0.0,
        val reliability: Double = 0.0,
        val reliabilityMessage:String = ""
) : CachedEntity(), DataModel<Product> {

    override fun toDomain(): Product {
        return Product(
                id = id,
                name = name,
                thumbnailUrl = thumbnailUrl,
                price = price,
                rating = rating
        )
    }

    fun toDetail() : ProductDetail {
        require(detailsAvailable)
        return ProductDetail(
                id = id,
                name = name,
                description = description ?: "",
                imagesUrls = images,
                advertisingMessages = advertisements,
                reliabilityPercentage = reliability,
                reliabilityMessage = reliabilityMessage,
                mainImageUrl = thumbnailUrl,
                rating = rating
        )
    }
}