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
        val reliabilityMessage:String = "",
        val availability: String,
        val availabilityPostfix: String
) : CachedEntity(), DataModel<Product> {

    override fun toDomain(): Product {
        return Product(
                id = id,
                name = name,
                thumbnailUrl = thumbnailUrl,
                price = price,
                rating = rating,
                details = buildDetails()
        )
    }

    private fun buildDetails() : ProductDetail? {
        return  if (detailsAvailable) {
            ProductDetail(
                    description = description ?: "",
                    imagesUrls = images,
                    advertisingMessages = advertisements,
                    reliabilityPercentage = reliability,
                    reliabilityMessage = reliabilityMessage,
                    availabilityPostfix = availabilityPostfix,
                    availability = availability
            )
        } else null
    }
}