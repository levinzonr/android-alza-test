package cz.levinzonr.spotistats.presentation.screens.main.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cz.levinzonr.spotistats.domain.models.Product

import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import cz.levinzonr.spotistats.presentation.util.GridSpaceItemDecoration
import cz.levinzonr.spotistats.presentation.util.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProductsFragment : BaseFragment<State>(), ProductsAdapter.ProductItemsListener {

    private val args by navArgs<ProductsFragmentArgs>()
    override val viewModel: ProductsViewModel by viewModel { parametersOf(args.categoryId)}
    private val adapter by lazy { ProductsAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun renderState(state: State) {
        progressBar.isVisible = state.isLoading
        adapter.submitList(state.products)
    }

    override fun onProductClicked(product: Product) {
        viewModel.dispatch(Action.ProductClicked(product))
    }

    private fun setupRecyclerView() {
        productsRv.layoutManager = GridLayoutManager(requireContext(), 2)
        productsRv.addItemDecoration(GridSpaceItemDecoration())
        productsRv.adapter = adapter
    }


}
