package cz.levinzonr.spotistats.network.models

import cz.levinzonr.spotistats.domain.repository.RepositoryException

data class AlzaResponse<T>(
    val alzaCredit: Any,
    val basket_cnt: Int,
    val basket_total_cnt: Int,
    val countryID: Int,
    val countryPhonePrefix: String,
    val `data`: T?,
    val data_cnt: Int,
    val err: Int,
    val favCnt: Int,
    val msg: String?,
    val premiumBooks: Int,
    val premiumDelivery: Int,
    val premiumMovies: Int,
    val premiumMusic: Int,
    val premiumRenew: Boolean,
    val premiumValidTo: Any,
    val serverTime: Int,
    val user_id: Int,
    val user_name: String,
    val vzt: Int
) {

    @Throws(RepositoryException::class)
    fun getDataOrThrow() : T {
        return data ?: throw RepositoryException(msg = msg ?: "Unknown API Error")
    }
}