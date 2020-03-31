package cz.levinzonr.spotistats.network

import cz.levinzonr.spotistats.network.models.*
import retrofit2.http.*

interface Api {
    @GET("v1/floors")
    suspend fun getProductCategoriesAsync() : AlzaResponse<List<CategoryResponse>>

    @GET("v13/product/5072141")
    suspend fun getProductDetailsAsync(id: String) : AlzaResponse<ProductDetailResponse>

    @POST("v2/products")
    suspend fun getProductFromCategories(
            @Body filterParams: FilterParams
    ) : AlzaResponse<List<ProductResponse>>
}
