package io.github.brunogabriel.rickmorty.main.deeplink

import android.content.Context
import android.content.Intent
import io.github.brunogabriel.rickmorty.deeplink.constants.DeeplinkConstants
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkProcessor
import io.github.brunogabriel.rickmorty.main.presentation.activity.MainActivity
import javax.inject.Inject

internal class MainDeeplinkProcessor @Inject constructor(
    private val context: Context
) : DeeplinkProcessor {
    override fun matches(deeplink: String) =
        deeplink.contains(DeeplinkConstants.MAIN)

    override fun execute(deeplink: String) = Intent(context, MainActivity::class.java)

}