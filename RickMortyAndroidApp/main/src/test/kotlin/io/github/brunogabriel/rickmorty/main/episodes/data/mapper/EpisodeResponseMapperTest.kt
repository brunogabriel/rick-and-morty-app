package io.github.brunogabriel.rickmorty.main.episodes.data.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.episodes.data.models.EpisodeResponse
import org.junit.Test

class EpisodeResponseMapperTest {
    @Test
    fun `should map response to entity`() {
        assertThat(
            episodeResponseToEntity(
                EpisodeResponse(
                    1,
                    "name",
                    "airDate",
                    "episode"
                )
            )
        ).isEqualTo(
            EpisodeEntity(
                1,
                "name",
                "airDate",
                "episode"
            )
        )
    }
}