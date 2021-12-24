package io.github.brunogabriel.rickmorty.main.locations.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationsListResponse(
    val results: List<LocationResponse>
)