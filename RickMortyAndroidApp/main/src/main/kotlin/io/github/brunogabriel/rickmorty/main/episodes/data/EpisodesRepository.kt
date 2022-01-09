package io.github.brunogabriel.rickmorty.main.episodes.data

import androidx.annotation.VisibleForTesting
import io.github.brunogabriel.rickmorty.datalocal.data.EpisodeDao
import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.episodes.data.mapper.episodeResponseToEntity
import io.github.brunogabriel.rickmorty.main.episodes.data.service.EpisodesService
import javax.inject.Inject

internal class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodesService,
    private val episodeDao: EpisodeDao
) : EpisodesRepository {

    override suspend fun getEpisodes(page: Int, forceRefresh: Boolean): List<EpisodeEntity> {
        return if (forceRefresh) {
            fetchFromService(page)
        } else {
            episodeDao.getEntities(PAGE_SIZE, (page - 1) * PAGE_SIZE).let {
                if (it.isEmpty()) {
                    fetchFromService(page)
                } else {
                    it
                }
            }
        }
    }

    private suspend fun fetchFromService(page: Int) =
        service.getEpisodes(page).results.map(::episodeResponseToEntity)
            .apply {
                episodeDao.insertAll(this)
            }

    @VisibleForTesting
    internal companion object {
        const val PAGE_SIZE = 20
    }
}

interface EpisodesRepository {
    suspend fun getEpisodes(
        page: Int,
        forceRefresh: Boolean
    ): List<EpisodeEntity>
}