package io.github.brunogabriel.rickmorty.main.episodes.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import io.github.brunogabriel.rickmorty.shared.recyclerview.adapter.GenericAdapter
import io.github.brunogabriel.rickmorty.styleguide.components.EpisodeRowView
import io.github.brunogabriel.rickmorty.styleguide.components.LoadingView
import io.github.brunogabriel.rickmorty.styleguide.components.TryAgainView

class EpisodeRecyclerViewAdapter(
    private val tryAgainAction: () -> Unit
) : GenericAdapter() {

    override fun getItemViewType(position: Int): Int {
        // TODO: Section Header
        return if (models[position] is EpisodeVO) {
            VIEW_TYPE_EPISODE
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

            VIEW_TYPE_EPISODE -> {
                EpisodeViewHolder(
                    EpisodeRowView(parent.context)
                )
            }
            else -> throw EpisodeRecyclerViewException("Fail onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeViewHolder -> {
                val vo = models[position] as EpisodeVO
                holder.bindView(vo.name, vo.episode, vo.airDate)
            }

            is TryAgainViewHolder -> {
                (holder.view as TryAgainView).bind(tryAgainAction)
            }
        }
    }

    internal class EpisodeViewHolder(private val row: EpisodeRowView) :
        RecyclerView.ViewHolder(row) {

        fun bindView(
            episodeSeason: String,
            title: String,
            releaseDate: String
        ) = with(row) {
            bind(episodeSeason, title, releaseDate)
        }
    }

    companion object {
        const val VIEW_TYPE_EPISODE = 3
    }

}