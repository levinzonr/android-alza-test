package cz.levinzonr.spotistats.presentation.screens.main.products

import cz.levinzonr.spotistats.domain.interactors.GetProductsFromCategoryInteractor
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.*
import cz.levinzonr.spotistats.presentation.navigation.Route
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
        private val getProductsFromCategoryInteractor: GetProductsFromCategoryInteractor
) : BaseViewModel<Action, Change, State>() {
    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when(change) {
            is Change.ProductsLoading -> state.copy(isLoading = true)
            is Change.ProductsLoaded -> state.copy(isLoading = false, products = change.items)
            is Change.Navigation -> state.also { navigateTo(change.route) }
            is Change.ProductLoadingError -> state.copy(
                    isLoading = false,
                    errorEvent = change.throwable.toViewErrorEvent()
            )
        }
    }
    init {
        startActionsObserver()
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when(action) {
            is Action.Init -> bindInitAction(action)
            is Action.ProductClicked -> bindProductClickedAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init) : Flow<Change> = flowOnIO {
        emit(Change.ProductsLoading)
        getProductsFromCategoryInteractor.asResult().invoke(action.categoryId)
                .isError { e -> emit(Change.ProductLoadingError(e)) }
                .isSuccess { emit(Change.ProductsLoaded(it)) }
    }

    private fun bindProductClickedAction(action: Action.ProductClicked) : Flow<Change> = flowOnIO {
        val dest = ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(action.product.id)
        emit(Change.Navigation(Route.Destination(dest)))
    }
}