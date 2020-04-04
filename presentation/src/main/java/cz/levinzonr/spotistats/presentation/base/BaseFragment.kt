package cz.levinzonr.spotistats.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.presentation.extensions.observeNonNull
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.screens.main.MainActivity
import cz.levinzonr.spotistats.presentation.screens.onboarding.OnboardingActivity
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import cz.levinzonr.spotistats.presentation.util.ViewError
import cz.levinzonr.spotistats.presentation.util.ViewErrorController
import org.koin.android.ext.android.inject
import timber.log.Timber

abstract class BaseFragment<S : BaseState> : Fragment() {

    abstract val viewModel: BaseViewModel<*, *, S>

    private lateinit var viewErrorController: ViewErrorController

    abstract fun renderState(state: S)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewErrorController = ViewErrorController(requireContext())
        viewModel.observableState.observeNonNull(viewLifecycleOwner) {
            Timber.d("${viewModel.javaClass.simpleName} state: $it")
            renderState(it)
        }

        viewModel.navigationLiveData.observeNonNull(viewLifecycleOwner) {
            Timber.d("Navigation Event: $it")
            it.consume()?.let(this::handleNavigationEvent)
        }
    }


    protected fun handleViewErrorEvent(event: SingleEvent<ViewError>?) {
        event?.consume()?.let { viewError ->
            viewErrorController.showErrorDialog(viewError, true) {
                findNavController().navigateUp()
            }
        }
    }

    private fun handleNavigationEvent(route: Route) {
        when (route) {
            is Route.Destination -> findNavController().navigate(route.navDirections)
            is Route.Main -> {
                activity?.finish()
                startActivity(MainActivity.createIntent(requireContext()))
            }
            is Route.Onboarding -> {
                activity?.finish()
                startActivity(Intent(context, OnboardingActivity::class.java))
            }
        }
    }


}
