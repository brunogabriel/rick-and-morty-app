package io.github.brunogabriel.rickmorty.main.episodes.domain.mapper

import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO

fun episodeEntityToVO(
    entity: EpisodeEntity
) = EpisodeVO(
    id = entity.id,
    name = entity.name,
    airDate = entity.airDate,
    episode = entity.episode
)