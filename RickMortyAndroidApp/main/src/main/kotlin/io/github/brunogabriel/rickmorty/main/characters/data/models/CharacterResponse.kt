package io.github.brunogabriel.rickmorty.main.characters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val image: String
)