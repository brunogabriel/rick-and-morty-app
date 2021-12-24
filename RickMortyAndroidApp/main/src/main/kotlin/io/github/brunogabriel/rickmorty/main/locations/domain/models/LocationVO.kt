package io.github.brunogabriel.rickmorty.main.locations.domain.models

data class LocationVO(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)