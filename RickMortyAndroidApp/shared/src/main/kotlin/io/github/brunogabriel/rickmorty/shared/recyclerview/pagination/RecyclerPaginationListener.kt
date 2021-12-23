package io.github.brunogabriel.rickmorty.shared.recyclerview.pagination

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerPaginationListener(
    private val layoutManager: GridLayoutManager,
    private val paginationReached: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        if ((lastVisibleItemPosition + THRESHOLD) > totalItemCount) {
            paginationReached()
        }
    }

    companion object {
        private const val THRESHOLD = 5
    }
}