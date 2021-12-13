package io.github.brunogabriel.rickmorty.main.characters.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import io.github.brunogabriel.rickmorty.styleguide.components.CharacterCardView

class CharacterRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val models = mutableListOf<Any>()

    fun addModels(newModels: CharacterAdapterViewType.Characters) {
        val total = models.size
        models.addAll(newModels.vos)
        notifyItemRangeInserted(total, newModels.vos.size)
    }

    override fun getItemViewType(position: Int): Int {
        return when (models[position]) {
            is CharacterAdapterViewType.Loading -> VIEW_TYPE_LOADING
            is CharacterAdapterViewType.TryAgain -> VIEW_TYPE_TRY_AGAIN
            is CharacterVO -> VIEW_TYPE_CHARACTERS
            else -> throw CharacterRecyclerVIewException("Fail getItemViewType")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                TODO("Not yet implemented")
            }

            VIEW_TYPE_TRY_AGAIN -> {
                TODO("Not yet implemented")
            }

            VIEW_TYPE_CHARACTERS -> {
                CharacterViewHolder(
                    CharacterCardView(parent.context)
                )
            }

            else -> throw CharacterRecyclerVIewException("Fail onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                val vo = models[position] as CharacterVO
                holder.bindView(vo.status, vo.name, vo.image)
            }
        }
    }

    override fun getItemCount() = models.size

    internal class CharacterViewHolder(private val card: CharacterCardView) :
        RecyclerView.ViewHolder(card) {

        fun bindView(
            contentTitle: String,
            contentBody: String,
            url: String
        ) = with(card) {
            bind(contentTitle, contentBody, url)
        }
    }

    companion object {
        private const val VIEW_TYPE_LOADING = 1
        private const val VIEW_TYPE_TRY_AGAIN = 2
        private const val VIEW_TYPE_CHARACTERS = 3
    }
}