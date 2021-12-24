package io.github.brunogabriel.rickmorty.main.characters.data.models

import kotlinx.serialization.Serializable

@Serializable
class CharactersListResponse(
    val results: List<CharacterResponse>
)