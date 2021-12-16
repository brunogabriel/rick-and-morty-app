package io.github.brunogabriel.rickmorty.shared.viewmodel

sealed class NavigationEvent {
    data class Deeplink(val link: String) : NavigationEvent()
    data class OpenUrl(val url: String) : NavigationEvent()
}