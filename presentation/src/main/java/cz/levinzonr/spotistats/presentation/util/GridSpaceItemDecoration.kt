package cz.levinzonr.spotistats.presentation.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cz.levinzonr.spotistats.presentation.extensions.dpToPx
import kotlin.math.acos

class GridSpaceItemDecoration(
    private val preferredSpaceDp: Int = SPACE_DEFAULT,
    private val columnCount: Int = COLUMN_COUNT_DEFAULT
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val space = view.dpToPx(preferredSpaceDp)
        outRect.bottom = space
        when(position % columnCount) {
            0 ->  {
                outRect.left = space
                outRect.right = space / 2
            }
            columnCount - 1 ->  {
                outRect.right = space
                outRect.left = space / 2
            }
            else -> {
                outRect.left = space / 2
                outRect.right = space / 2
            }
        }
       if (position in 0 until COLUMN_COUNT_DEFAULT) outRect.top = space
    }

    companion object {
        private const val COLUMN_COUNT_DEFAULT = 2
        private const val SPACE_DEFAULT = 16
    }
}