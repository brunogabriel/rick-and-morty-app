package io.github.brunogabriel.rickmorty.main.episodes.data.mapper

import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.episodes.data.models.EpisodeResponse

fun episodeResponseToEntity(
    response: EpisodeResponse
) = EpisodeEntity(
    id = response.id,
    name = response.name,
    airDate = response.airDate,
    episode = response.episode
)