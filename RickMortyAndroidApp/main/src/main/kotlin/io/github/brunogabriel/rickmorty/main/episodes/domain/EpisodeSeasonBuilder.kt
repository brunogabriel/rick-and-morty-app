package io.github.brunogabriel.rickmorty.main.episodes.domain

import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import javax.inject.Inject

internal class EpisodeSeasonBuilderImpl @Inject constructor() : EpisodeSeasonBuilder {

    private val sectionsCreated = mutableSetOf<Int>()

    override fun addSeason(episodes: List<EpisodeVO>): List<Any> =
        episodes
            .groupBy { it.episode.substring(1, 3).toInt() }
            .map { (season, seasonEpisodes) ->
                if (sectionsCreated.contains(season).not()) {
                    sectionsCreated.add(season)
                    listOf<Any>("Season $season")
                } else {
                    emptyList()
                }.plus(seasonEpisodes)
            }
            .flatten()

}

interface EpisodeSeasonBuilder {
    fun addSeason(episodes: List<EpisodeVO>): List<Any>
}