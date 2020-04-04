package cz.levinzonr.spotistats.presentation.screens.main.products

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import cz.levinzonr.spotistats.presentation.util.ViewError


data class State(
        val isLoading: Boolean = false,
        val products : List<Product> = listOf(),
        val errorEvent: SingleEvent<ViewError>? = null
) : BaseState

sealed class Change : BaseChange {
    object ProductsLoading: Change()
    data class ProductsLoaded(val items: List<Product>) : Change()
    data class Navigation(val route: Route) : Change()
    data class ProductLoadingError(val throwable: Throwable) :Change()
}

sealed class Action : BaseAction {
    data class Init(val categoryId: String) : Action()
    data class ProductClicked(val product: Product) : Action()
}
