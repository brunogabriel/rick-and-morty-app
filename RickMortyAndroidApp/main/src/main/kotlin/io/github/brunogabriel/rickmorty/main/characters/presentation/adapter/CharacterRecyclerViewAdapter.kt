package io.github.brunogabriel.rickmorty.main.characters.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import io.github.brunogabriel.rickmorty.shared.recyclerview.adapter.GenericAdapter
import io.github.brunogabriel.rickmorty.styleguide.components.CharacterCardView
import io.github.brunogabriel.rickmorty.styleguide.components.LoadingView
import io.github.brunogabriel.rickmorty.styleguide.components.TryAgainView

class CharacterRecyclerViewAdapter(
    private val tryAgainAction: () -> Unit
) : GenericAdapter() {

    override fun getItemViewType(position: Int): Int {
        return if (models[position] is CharacterVO) {
            VIEW_TYPE_CHARACTER
        } else {
            super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LOADING -> LoadingViewHolder(
                LoadingView(parent.context)
            )
            VIEW_TYPE_TRY_AGAIN -> {
                TryAgainViewHolder(
                    TryAgainView(parent.context)
                )
            }

            VIEW_TYPE_CHARACTER -> {
                CharacterViewHolder(
                    CharacterCardView(parent.context)
                )
            }
            else -> throw CharacterRecyclerViewException("Fail onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                val vo = models[position] as CharacterVO
                holder.bindView(vo.status, vo.name, vo.image)
            }

            is TryAgainViewHolder -> {
                (holder.view as TryAgainView).bind(tryAgainAction)
            }
        }
    }

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
        const val VIEW_TYPE_CHARACTER = 3
    }

}