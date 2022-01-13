package io.github.brunogabriel.rickmorty.shared.viewmodel

sealed class NavigationEvent {
    data class Deeplink(val deeplink: String) : NavigationEvent()
    data class Url(val url: String) : NavigationEvent()
}