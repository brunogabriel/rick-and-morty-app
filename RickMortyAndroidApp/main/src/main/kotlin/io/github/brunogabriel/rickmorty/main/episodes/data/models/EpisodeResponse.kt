package io.github.brunogabriel.rickmorty.main.episodes.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    @SerialName("air_date")
    val airDate: String,
    val episode: String
)