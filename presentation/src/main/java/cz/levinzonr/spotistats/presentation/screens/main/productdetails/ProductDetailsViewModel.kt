package cz.levinzonr.spotistats.presentation.screens.main.productdetails

import cz.levinzonr.spotistats.domain.interactors.GetProductByIdInteractor
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.asResult
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class ProductDetailsViewModel(
        id: String,
        private val getProductByIdInteractor: GetProductByIdInteractor
): BaseViewModel<Action, Change, State>() {
    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State =  {state, change ->
        when(change) {
            is Change.ProductDetailsLoaded -> state.copy(isLoading = false, product = change.product)
            is Change.ProductDetailsLoading -> state.copy(isLoading = true)
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init(id))
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when(action) {
            is Action.Init -> bindInitAction(action)
        }
    }

    private fun bindInitAction(action: Action.Init) : Flow<Change> = flowOnIO {
        emit(Change.ProductDetailsLoading)
        getProductByIdInteractor.asResult().invoke(action.id)
                .isSuccess { emit(Change.ProductDetailsLoaded(it)) }
                .isError { Timber.e(it) }
    }
}