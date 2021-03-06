package cz.levinzonr.spotistats.presentation.screens.main.categories

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import cz.levinzonr.spotistats.domain.models.Category
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesAdapter(
        private val listener: CategoryItemsListener
) : ListAdapter<Category, CategoriesAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_category))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(category: Category) {
            view.categoryImageIv.load(category.imageUrl)
            view.categoryNameTv.text = category.name
            view.setOnClickListener { listener.onCategoryClicked(category) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    interface CategoryItemsListener {
        fun onCategoryClicked(category: Category)
    }
}