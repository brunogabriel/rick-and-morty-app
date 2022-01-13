package io.github.brunogabriel.rickmorty.main.episodes.domain

import io.github.brunogabriel.rickmorty.main.episodes.data.EpisodesRepository
import io.github.brunogabriel.rickmorty.main.episodes.domain.mapper.episodeEntityToVO
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import javax.inject.Inject

interface EpisodesUseCase {
    suspend fun fetchAll(page: Int, forceRefresh: Boolean): List<EpisodeVO>
}

internal class EpisodesUseCaseImpl @Inject constructor(
    private val repository: EpisodesRepository
) : EpisodesUseCase {
    override suspend fun fetchAll(page: Int, forceRefresh: Boolean): List<EpisodeVO> {
        return repository.getEpisodes(page, forceRefresh)
            .map(::episodeEntityToVO)
    }
}