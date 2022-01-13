package io.github.brunogabriel.rickmorty.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import io.github.brunogabriel.rickmorty.styleguide.databinding.EpisodeRowViewBinding

class EpisodeRowView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RelativeLayout(context, attributeSet) {

    private val binding: EpisodeRowViewBinding =
        EpisodeRowViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    fun bind(episodeSeason: String, title: String, releaseDate: String) {
        with(binding) {
            episodeSeasonText.text = episodeSeason
            titleText.text = title
            releaseText.text = releaseDate
        }
    }
}