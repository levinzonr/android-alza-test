package cz.levinzonr.spotistats.presentation.screens.main.productdetails

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.ProductDetail

data class State(
        val isLoading: Boolean = false,
        val product: ProductDetail? = null
) : BaseState

sealed class Change : BaseChange {
    object ProductDetailsLoading: Change()
    data class ProductDetailsLoaded(val product: ProductDetail) : Change()
}

sealed class Action: BaseAction {
    data class Init(val id: String) : Action()
}