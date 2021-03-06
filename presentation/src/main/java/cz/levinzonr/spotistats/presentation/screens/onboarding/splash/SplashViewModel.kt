package cz.levinzonr.spotistats.presentation.screens.onboarding.splash

import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel<Action, Change, State>() {


    override val initialState: State = State(false)


    override val reducer: suspend (state: State, change: Change) -> State = {state, change ->
        when(change) {
            is Change.InitStarted -> state.copy(isLoading = true)
            is Change.InitFinished -> state.copy(isLoading = false).also {
                if (change.showMain) navigateTo(Route.Main) else navigateToLogin()
            }
        }
    }

    init {
        startActionsObserver()
    }


    override fun emitAction(action: Action): Flow<Change> {
       return when(action) {
           is Action.Init -> bindInitAction()
       }
    }

    private fun navigateToLogin() {
        val dest = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        navigateTo(Route.Destination(dest))
    }

    private fun bindInitAction() : Flow<Change> = flow {
        emit(Change.InitStarted)
        delay(2000)
        emit(Change.InitFinished(true))
    }
}