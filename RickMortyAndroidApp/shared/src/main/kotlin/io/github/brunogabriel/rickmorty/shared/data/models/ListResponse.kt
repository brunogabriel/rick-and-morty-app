package io.github.brunogabriel.rickmorty.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ListResponse<T>(
    val results: List<T>
)