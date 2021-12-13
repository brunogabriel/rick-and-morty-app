package io.github.brunogabriel.rickmorty.main.characters.domain.models

data class CharacterVO(
    val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val image: String
)