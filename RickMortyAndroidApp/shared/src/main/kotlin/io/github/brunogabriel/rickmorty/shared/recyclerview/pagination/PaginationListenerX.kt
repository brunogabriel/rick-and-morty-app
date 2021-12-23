package io.github.brunogabriel.rickmorty.shared.recyclerview.pagination

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.paginationListener(layoutManager: LinearLayoutManager, listener: (Int) -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        private var previousTotal = 0
        private var loading = true
        private var currentPage = 0
        override fun onScrolled(
            recyclerView: RecyclerView,
            dx: Int,
            dy: Int
        ) {
            val visibleItemCount = recyclerView.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            if (previousTotal > totalItemCount) {
                previousTotal = 0
                currentPage = 0
                loading = true
            }
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            val visibleThreshold = 4
            if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                currentPage++
                listener(currentPage)
                loading = true
            }
        }
    })
}