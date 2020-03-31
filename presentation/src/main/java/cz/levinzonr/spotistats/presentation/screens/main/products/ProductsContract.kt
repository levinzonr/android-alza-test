package cz.levinzonr.spotistats.presentation.screens.main.products

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.presentation.navigation.Route


data class State(
        val isLoading: Boolean = false,
        val products : List<Product> = listOf()
) : BaseState

sealed class Change : BaseChange {
    object ProductsLoading: Change()
    data class ProductsLoaded(val items: List<Product>) : Change()
    data class Navigation(val route: Route) : Change()
}

sealed class Action : BaseAction {
    data class Init(val categoryId: String) : Action()
    data class ProductClicked(val product: Product) : Action()
}
