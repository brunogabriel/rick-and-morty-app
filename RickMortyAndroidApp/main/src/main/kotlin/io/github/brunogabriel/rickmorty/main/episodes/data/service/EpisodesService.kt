package io.github.brunogabriel.rickmorty.main.episodes.data.service

import io.github.brunogabriel.rickmorty.main.episodes.data.models.EpisodeResponse
import io.github.brunogabriel.rickmorty.shared.data.models.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesService {
    @GET("episode")
    suspend fun getEpisodes(
        @Query("page") page: Int
    ): ListResponse<EpisodeResponse>
}