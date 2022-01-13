package io.github.brunogabriel.rickmorty.onboard.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.github.brunogabriel.rickmorty.deeplink.constants.DeeplinkConstants
import io.github.brunogabriel.rickmorty.shared.viewmodel.NavigationEvent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class OnboardViewModel @Inject constructor() : ViewModel() {

    @VisibleForTesting
    internal val deeplinkEvent = MutableStateFlow<NavigationEvent?>(null)

    fun showProject() {
        deeplinkEvent.value =
            NavigationEvent.Url(PROJECT_URL)
    }

    fun showMain() {
        deeplinkEvent.value = NavigationEvent.Deeplink(DeeplinkConstants.MAIN)
    }

    companion object {
        private const val PROJECT_URL = "https://github.com/brunogabriel/rick-and-morty-app"
    }
}