package cz.levinzonr.spotistats.network.models

import cz.levinzonr.spotistats.domain.models.DataModel
import cz.levinzonr.spotistats.domain.models.Product

data class ProductResponse(
        val action_name: String,
        val advertising: String,
        val amountInPack: Int,
        val avail: String,
        val availLegend: Any,
        val avail_color: String,
        val avail_postfix: String,
        val avail_postfix2: Any,
        val canCashBack: Boolean,
        val canChangeQuantity: Boolean,
        val can_buy: Boolean,
        val cashBackType: Int,
        val catalog_number: String,
        val categoryName: String?,
        val code: String,
        val cprice: String,
        val end_time: Any,
        val id: Int,
        val img: String?,
        val inBasket: Int,
        val is_action: Boolean,
        val is_special_service: Boolean,
        val itemType: String,
        val maxCanBuy: Int,
        val minimumAmount: Int,
        val name: String,
        val order: Int,
        val orderItemId: Any,
        val price: String,
        val priceNoCurrency: Int,
        val priceWithoutVat: String,
        val promo_cnt: Int,
        val promos: List<PromoResponse>,
        val rating: Double,
        val spec: String,
        val start_time: Any,
        val supplierCode: String,
        val type: Int,
        val url: String,
        val userOwns: Boolean,
        val variant_type: Int
) : DataModel<Product> {
    override fun toDomain(): Product {
        return Product(
                id = id.toString(),
                name = name,
                price = priceNoCurrency.toDouble(),
                thumbnailUrl = img,
                rating = rating,
                details = null
        )
    }
}