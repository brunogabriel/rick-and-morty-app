package io.github.brunogabriel.rickmorty.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import io.github.brunogabriel.rickmorty.shared.extensions.loadImage
import io.github.brunogabriel.rickmorty.styleguide.databinding.CharacterCardViewBinding

class CharacterCardView(
    context: Context,
    attributeSet: AttributeSet? = null
) : MaterialCardView(context, attributeSet) {
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