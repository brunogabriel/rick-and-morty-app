package io.github.brunogabriel.rickmorty.deeplink.domain

import android.content.Intent
import javax.inject.Inject

internal class DeeplinkHandlerImpl @Inject constructor(
    private val processors: Set<@JvmSuppressWildcards DeeplinkProcessor>
) : DeeplinkHandler {
    override fun process(deeplink: String) =
        processors.firstOrNull {
            it.matches(deeplink)
        }?.execute(deeplink)
}

interface DeeplinkHandler {
    fun process(deeplink: String): Intent?
}