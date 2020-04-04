package cz.levinzonr.spotistats.presentation.screens.main.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cz.levinzonr.spotistats.domain.models.Category

import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import cz.levinzonr.spotistats.presentation.base.BaseViewModel
import cz.levinzonr.spotistats.presentation.util.GridSpaceItemDecoration
import cz.levinzonr.spotistats.presentation.util.VerticalSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : BaseFragment<State>(), CategoriesAdapter.CategoryItemsListener{

    override val viewModel: CategoriesViewModel by viewModel()
    private val adapter by lazy { CategoriesAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun renderState(state: State) {
        progressBar.isVisible = state.isLoading
        adapter.submitList(state.categories)
        handleViewErrorEvent(state.errorDialogEvent)
    }

    private fun setupRecyclerView() {
        categoriesRv.layoutManager = GridLayoutManager(requireContext(), 2)
        categoriesRv.addItemDecoration(GridSpaceItemDecoration())
        categoriesRv.adapter = adapter
    }

    override fun onCategoryClicked(category: Category) {
        viewModel.dispatch(Action.CategoryClicked(category))
    }
}
