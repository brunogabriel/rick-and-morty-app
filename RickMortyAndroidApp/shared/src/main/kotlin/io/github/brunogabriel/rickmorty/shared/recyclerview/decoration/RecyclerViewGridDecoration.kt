package io.github.brunogabriel.rickmorty.shared.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewGridDecoration(
    private val margin: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val lastRow = parent.childCount > 0 && position >= parent.childCount - 2
        with(outRect) {
            if (position % 2 == 0) {
                left = margin
                right = margin / 2
            } else {
                right = margin
                left = margin / 2
            }
            bottom = margin
        }
    }
}