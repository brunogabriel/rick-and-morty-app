package io.github.brunogabriel.rickmorty.deeplink.domain

import android.content.Intent

interface DeeplinkProcessor {
    fun matches(deeplink: String): Boolean
    fun execute(deeplink: String): Intent
}