package cz.levinzonr.spotistats.presentation.screens.main.productdetails

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.domain.models.ProductDetail
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import cz.levinzonr.spotistats.presentation.util.ViewError

data class State(
        val isLoading: Boolean = false,
        val product: Product? = null,
        val errorEvent: SingleEvent<ViewError>? = null
) : BaseState

sealed class Change : BaseChange {
    object ProductDetailsLoading: Change()
    data class ProductDetailsLoaded(val product: Product) : Change()
    data class ProductDetailsLoadingError(val error: Throwable) : Change()
}

sealed class Action: BaseAction {
    data class Init(val id: String) : Action()
}