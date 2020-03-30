package cz.levinzonr.spotistats.presentation.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cz.levinzonr.spotistats.presentation.extensions.dpToPx

class VerticalSpaceItemDecoration(val spaceDp: Int = SPACE_DEFAULT) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val totalCnt = parent.adapter?.itemCount ?: 0
        val spaceTop = when(position) {
            0 -> view.dpToPx(spaceDp)
            else -> view.dpToPx(spaceDp/2)
        }
        val spaceBottom = when(position) {
            totalCnt -> view.dpToPx(spaceDp)
            else -> view.dpToPx(spaceDp / 2)
        }
        outRect.bottom = spaceBottom
        outRect.top = spaceTop
    }

    companion object {
        private const val SPACE_DEFAULT = 16
    }
}