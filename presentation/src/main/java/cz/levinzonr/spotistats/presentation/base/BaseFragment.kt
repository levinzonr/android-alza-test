package cz.levinzonr.spotistats.presentation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cz.levinzonr.roxie.BaseState
import cz.levinzonr.spotistats.presentation.extensions.getSharedViewModel
import cz.levinzonr.spotistats.presentation.extensions.getViewModel
import cz.levinzonr.spotistats.presentation.extensions.lifecycleAwareLazy
import cz.levinzonr.spotistats.presentation.extensions.observeNonNull
import cz.levinzonr.spotistats.presentation.navigation.Route
import cz.levinzonr.spotistats.presentation.screens.main.MainActivity
import cz.levinzonr.spotistats.presentation.screens.onboarding.OnboardingActivity
import cz.levinzonr.spotistats.presentation.util.SingleEvent
import cz.levinzonr.spotistats.presentation.util.ViewError
import cz.levinzonr.spotistats.presentation.util.ViewErrorController
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<S : BaseState> : Fragment(), HasAndroidInjector {

    abstract val viewModel: BaseViewModel<*, *, S>

    private lateinit var viewErrorController: ViewErrorController

    abstract fun renderState(state: S)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    protected inline fun <reified VM : ViewModel> getViewModel(): VM =
            getViewModel(viewModelFactory)

    protected inline fun <reified VM : ViewModel> getSharedViewModel(): VM =
            getSharedViewModel(viewModelFactory)

    protected inline fun <reified VM : ViewModel> viewModel(): Lazy<VM> = lifecycleAwareLazy(this) {
        getViewModel<VM>()
    }

    protected inline fun <reified VM : ViewModel> sharedViewModel(): Lazy<VM> =
            lifecycleAwareLazy(this) {
                getSharedViewModel<VM>()
            }

    override fun androidInjector() = androidInjector

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
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
