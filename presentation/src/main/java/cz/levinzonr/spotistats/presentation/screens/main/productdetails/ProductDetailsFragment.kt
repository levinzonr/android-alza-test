package cz.levinzonr.spotistats.presentation.screens.main.productdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import coil.api.load
import cz.levinzonr.spotistats.domain.models.Product
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_product_details.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailsFragment : BaseFragment<State>() {
    private val args by navArgs<ProductDetailsFragmentArgs>()

    override val viewModel: ProductDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dispatch(Action.Init(args.productId))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun renderState(state: State) {
        progressBar.isVisible = state.isLoading
        group.isVisible = !state.isLoading && state.product != null
        showProductDetails(state.product)
        handleViewErrorEvent(state.errorEvent)
    }

    private fun showProductDetails(productDetails: Product?) {
        productDetails?.let { product ->
            productNameTv.text = product.name
            productAvailabilityTittle.text = product.details?.availability
            productAvailabilitySubtitleTv.text = product.details?.availabilityPostfix
            productDescriptionTv.text = product.details?.description
            productImageIv.load(product.details?.imagesUrls?.firstOrNull())
            productPromoTv.text = product.details?.advertisingMessages?.firstOrNull()
            productRatingBar.rating = product.rating.toFloat()
            productRatingTv.text = "%.1f".format(product.rating)
            productPriceTv.text = "${product.price} KČ"
            productReliabilityTv.text = "Reliability of rating of ${product.details?.reliabilityPercentage}% \n ${product.details?.reliabilityMessage}"
        }
    }
}
