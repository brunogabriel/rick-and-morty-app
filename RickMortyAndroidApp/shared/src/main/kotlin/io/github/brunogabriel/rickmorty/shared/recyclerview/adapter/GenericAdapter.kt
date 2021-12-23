package io.github.brunogabriel.rickmorty.shared.recyclerview.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val models = mutableListOf<Any>()

    override fun getItemViewType(position: Int): Int {
        return when (models[position]) {
            is GenericItem.Loading -> VIEW_TYPE_LOADING
            is GenericItem.TryAgain -> VIEW_TYPE_TRY_AGAIN
            else -> -1
        }
    }

    override fun getItemCount() = models.size

    fun addModels(genericView: GenericItem.View) {
        genericView.items.let {
            val size = models.size
            removeLoadingOrTryAgain()
            models.addAll(it)
            notifyItemRangeInserted(size, it.size)
        }
    }

    fun addLoading() {
        if (isLastItemLoading().not() && isLastItemTryAgain().not()) {
            val position = models.size
            models.add(GenericItem.Loading)
            notifyItemInserted(position)
        }
    }

    fun addTryAgain() {
        removeLoadingOrTryAgain()
        if (isLastItemLoading().not() && isLastItemTryAgain().not()) {
            val position = models.size
            models.add(GenericItem.TryAgain)
            notifyItemInserted(position)
        }
    }

    fun removeLoadingOrTryAgain() {
        if (isLastItemLoading() || isLastItemTryAgain()) {
            val position = models.size - 1
            models.removeLastOrNull()
            notifyItemRemoved(position)
        }
    }

    fun isLoadingOrTryAgainAtPosition(position: Int) =
        itemCount > 0 && getItemViewType(position) in listOf(
            VIEW_TYPE_LOADING,
            VIEW_TYPE_TRY_AGAIN
        )

    fun isLastItemLoadingOrTryAgain() = isLastItemLoading() || isLastItemTryAgain()

    private fun isLastItemLoading() =
        itemCount > 0 && getItemViewType(itemCount - 1) == VIEW_TYPE_LOADING

    private fun isLastItemTryAgain() =
        itemCount > 0 && getItemViewType(itemCount - 1) == VIEW_TYPE_TRY_AGAIN

    class LoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    class TryAgainViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    companion object {
        const val VIEW_TYPE_LOADING = 1
        const val VIEW_TYPE_TRY_AGAIN = 2
    }
}