package cz.levinzonr.spotistats.presentation.screens.main.productdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs

import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import kotlinx.android.synthetic.main.fragment_product_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : BaseFragment<State>() {
    private val args by navArgs<ProductDetailsFragmentArgs>()

    override val viewModel: ProductDetailsViewModel by viewModel { parametersOf(args.productId)}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun renderState(state: State) {
        progressBar.isVisible = state.isLoading
        productNameTv.text = state.product?.name
    }
}
