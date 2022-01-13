package io.github.brunogabriel.rickmorty.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import io.github.brunogabriel.rickmorty.styleguide.databinding.LoadingViewBinding

class LoadingView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RelativeLayout(context, attributeSet) {

    private val binding: LoadingViewBinding =
        LoadingViewBinding.inflate(LayoutInflater.from(context), this, true)

}