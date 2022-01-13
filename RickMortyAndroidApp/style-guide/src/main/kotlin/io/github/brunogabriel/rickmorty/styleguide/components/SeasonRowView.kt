package io.github.brunogabriel.rickmorty.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.github.brunogabriel.rickmorty.styleguide.databinding.SeasonRowViewBinding

class SeasonRowView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    private val binding: SeasonRowViewBinding =
        SeasonRowViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    fun bind(season: String) {
        binding.seasonSectionText.text = season
    }
}