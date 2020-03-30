package cz.levinzonr.spotistats.presentation.screens.main.categories

import cz.levinzonr.spotistats.domain.interactors.GetProductCategoriesInteractor
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.extensions.asResult
import cz.levinzonr.spotistats.presentation.extensions.flowOnIO
import cz.levinzonr.spotistats.presentation.extensions.isError
import cz.levinzonr.spotistats.presentation.extensions.isSuccess
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class CategoriesViewModel(
        private val getProductCategoriesInteractor: GetProductCategoriesInteractor
) : BaseViewModel<Action, Change, State>() {

    override val initialState: State = State()

    override val reducer: suspend (state: State, change: Change) -> State = { state, change ->
        when (change) {
            is Change.CategoriesLoaded -> state.copy(
                    isLoading = false,
                    categories = change.list
            )
            is Change.CategoriesLoading -> state.copy(isLoading = true)
        }
    }

    init {
        startActionsObserver()
        dispatch(Action.Init)
    }

    override fun emitAction(action: Action): Flow<Change> {
        return when (action) {
            is Action.Init -> bindInitAction()
        }
    }

    private fun bindInitAction(): Flow<Change> = flowOnIO {
        emit(Change.CategoriesLoading)
        getProductCategoriesInteractor.asResult().invoke(Unit)
                .isSuccess { emit(Change.CategoriesLoaded(it)) }
                .isError { Timber.e(it) }
    }
}