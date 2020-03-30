package cz.levinzonr.spotistats.network

import cz.levinzonr.spotistats.network.models.AlzaResponse
import cz.levinzonr.spotistats.network.models.CategoryResponse
import cz.levinzonr.spotistats.network.models.ProductDetailResponse
import cz.levinzonr.spotistats.network.models.ProductsFilterParams
import retrofit2.http.*

interface Api {
    @GET("v1/floors")
    suspend fun getProductCategoriesAsync() : AlzaResponse<List<CategoryResponse>>

    @GET("v13/product/5072141")
    suspend fun getProductDetailsAsync(id: String) : AlzaResponse<ProductDetailResponse>

    @POST("v2/products")
    suspend fun getProductFromCategories(
            @Field("filterParameters") productsFilterParams: ProductsFilterParams
    ) : AlzaResponse<Any>
}
