package io.github.brunogabriel.rickmorty.main.locations.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO
import io.github.brunogabriel.rickmorty.shared.recyclerview.adapter.GenericAdapter
import io.github.brunogabriel.rickmorty.styleguide.components.LoadingView
import io.github.brunogabriel.rickmorty.styleguide.components.LocationCardView
import io.github.brunogabriel.rickmorty.styleguide.components.TryAgainView

class LocationRecyclerViewAdapter(
    private val tryAgainAction: () -> Unit
) : GenericAdapter() {

    override fun getItemViewType(position: Int): Int {
        return if (models[position] is LocationVO) {
            VIEW_TYPE_LOCATION
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

            VIEW_TYPE_LOCATION -> {
                LocationViewHolder(
                    LocationCardView(parent.context)
                )
            }
            else -> throw LocationRecyclerViewException("Fail onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LocationViewHolder -> {
                val vo = models[position] as LocationVO
                holder.bindView(vo.type, vo.name)
            }

            is TryAgainViewHolder -> {
                (holder.view as TryAgainView).bind(tryAgainAction)
            }
        }
    }

    internal class LocationViewHolder(private val card: LocationCardView) :
        RecyclerView.ViewHolder(card) {

        fun bindView(
            contentTitle: String,
            contentBody: String
        ) = with(card) {
            bind(contentTitle, contentBody)
        }
    }

    companion object {
        const val VIEW_TYPE_LOCATION = 3
    }

}