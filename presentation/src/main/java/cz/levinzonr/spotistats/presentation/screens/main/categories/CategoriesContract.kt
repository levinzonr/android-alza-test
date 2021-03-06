package cz.levinzonr.spotistats.presentation.screens.main.categories

import cz.levinzonr.roxie.BaseAction
import cz.levinzonr.roxie.BaseChange
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import cz.levinzonr.spotistats.presentation.util.ViewError

data class State(
        val categories: List<Category> = listOf(),
        val isLoading: Boolean = false,
        val errorDialogEvent: SingleEvent<ViewError>? = null
) : BaseState

sealed class Change : BaseChange {
    data class CategoriesLoaded(val list: List<Category>) : Change()
    object CategoriesLoading: Change()
    data class Navigation(val route: Route) : Change()
    data class CategoriesLoadingError(val error: Throwable) : Change()
}

sealed class Action : BaseAction{
    object Init: Action()
    data class CategoryClicked(val category: Category) : Action()
}