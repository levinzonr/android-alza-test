package cz.levinzonr.spotistats.presentation.screens.main.products

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsAdapter(
        private val listener: ProductItemsListener
) : ListAdapter<Product, ProductsAdapter.ViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_product))
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(product: Product) {
            view.productImageIv.load(product.thumbnailUrl)
            view.productNameTv.text = product.name
            view.ratingBar.rating = product.rating.toFloat()
            view.productPriceTv.text = product.price.toString()
            view.setOnClickListener { listener.onProductClicked(product) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    interface ProductItemsListener {
        fun onProductClicked(product: Product)
    }
}