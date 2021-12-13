package io.github.brunogabriel.rickmorty.main.characters.presentation.adapter

import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO

sealed class CharacterAdapterViewType {
    data class Characters(val vos: List<CharacterVO>) : CharacterAdapterViewType()
    object Loading : CharacterAdapterViewType()
    object TryAgain : CharacterAdapterViewType()
}