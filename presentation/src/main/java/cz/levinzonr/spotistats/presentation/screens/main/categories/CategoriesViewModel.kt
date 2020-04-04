package cz.levinzonr.spotistats.presentation.screens.main.categories

import cz.levinzonr.spotistats.domain.interactors.GetProductCategoriesInteractor
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.*
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.util.ViewErrorController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class CategoriesViewModel(
        private val getProductCategoriesInteractor: GetProductCategoriesInteractor
) : BaseViewModel<Action, Change, State>() {

    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when (change) {
            is Change.CategoriesLoading -> state.copy(isLoading = true)
            is Change.Navigation -> state.also { navigateTo(change.route) }
            is Change.CategoriesLoaded -> state.copy(
                    isLoading = false,
                    categories = change.list
            )
            is Change.CategoriesLoadingError -> state.copy(
                    isLoading = false,
                    errorDialogEvent = change.error.toViewErrorEvent()
            )
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init)
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when (action) {
            is Action.Init -> bindInitAction()
            is Action.CategoryClicked -> bindCategoryClickAction(action)
        }
    }

    private fun bindInitAction(): Flow<Change> = flowOnIO {
        emit(Change.CategoriesLoading)
        getProductCategoriesInteractor.asResult().invoke(Unit)
                .isSuccess { emit(Change.CategoriesLoaded(it)) }
                .isError { emit(Change.CategoriesLoadingError(it)) }
    }

    private fun bindCategoryClickAction(action: Action.CategoryClicked) : Flow<Change> = flow {
        val dest = CategoriesFragmentDirections.actionCategoriesFragmentToProductsFragment(action.category.id)
        emit(Change.Navigation(Route.Destination(dest)))
    }
}