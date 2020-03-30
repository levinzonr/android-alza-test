package cz.levinzonr.spotistats.network.models

data class AlzaResponse<T>(
    val alzaCredit: Any,
    val basket_cnt: Int,
    val basket_total_cnt: Int,
    val countryID: Int,
    val countryPhonePrefix: String,
    val `data`: List<T>,
    val data_cnt: Int,
    val err: Int,
    val favCnt: Int,
    val msg: Any,
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
)