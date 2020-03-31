package cz.levinzonr.spotistats.presentation.screens.main.products

import cz.levinzonr.spotistats.domain.interactors.GetProductsFromCategoryInteractor
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.asResult
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class ProductsViewModel(
        categoryId: String,
        private val getProductsFromCategoryInteractor: GetProductsFromCategoryInteractor
) : BaseViewModel<Action, Change, State>() {
    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when(change) {
            is Change.ProductsLoading -> state.copy(isLoading = true)
            is Change.ProductsLoaded -> state.copy(isLoading = false, products = change.items)
            is Change.Navigation -> state.also { navigateTo(change.route) }
        }
    }
    init {
        startActionsObserver()
        dispatch(Action.Init(categoryId))
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when(action) {
            is Action.Init -> bindInitAction(action)
            is Action.ProductClicked -> bindProductClickedAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init) : Flow<Change> = flowOnIO {
        getProductsFromCategoryInteractor.asResult().invoke(action.categoryId)
                .isError { e -> emit(Change.ProductsLoaded(listOf())).also { Timber.e(e) } }
                .isSuccess { emit(Change.ProductsLoaded(it)) }
    }

    private fun bindProductClickedAction(action: Action.ProductClicked) : Flow<Change> = flowOnIO {

    }
}