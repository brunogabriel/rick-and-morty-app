package io.github.brunogabriel.rickmorty.main.characters.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterInfoResponse(
    val count: Int?,
    val pages: Int?,
    val prev: String?,
    val next: String?
)