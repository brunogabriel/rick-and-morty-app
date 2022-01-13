package io.github.brunogabriel.rickmorty.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import io.github.brunogabriel.rickmorty.shared.extensions.loadImage
import io.github.brunogabriel.rickmorty.styleguide.R
import io.github.brunogabriel.rickmorty.styleguide.databinding.CharacterCardViewBinding

class CharacterCardView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = R.style.Theme_RickMortyAndroidApp_MaterialCard
) : MaterialCardView(context, attributeSet, style) {

    private val binding: CharacterCardViewBinding =
        CharacterCardViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    fun bind(contentTitle: String, contentBody: String, url: String) {
        with(binding) {
            title.text = contentTitle
            content.text = contentBody
            characterImage.loadImage(url, cached = true)
        }
    }
}