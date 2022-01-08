package io.github.brunogabriel.rickmorty.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import io.github.brunogabriel.rickmorty.styleguide.databinding.TryAgainViewBinding

class TryAgainView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RelativeLayout(context, attributeSet) {

    private val binding: TryAgainViewBinding =
        TryAgainViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(tryAgainAction: () -> Unit) {
        binding.tryAgainButton.setOnClickListener { tryAgainAction() }
    }
}