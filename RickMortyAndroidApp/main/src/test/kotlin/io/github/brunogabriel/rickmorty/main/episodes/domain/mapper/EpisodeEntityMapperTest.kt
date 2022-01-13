package io.github.brunogabriel.rickmorty.main.episodes.domain.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import org.junit.Test

class EpisodeEntityMapperTest {
    @Test
    fun `should map entity to vo`() {
        assertThat(
            episodeEntityToVO(
                EpisodeEntity(
                    1,
                    "name",
                    "airDate",
                    "episode"
                )
            )
        ).isEqualTo(
            EpisodeVO(
                1,
                "name",
                "airDate",
                "episode"
            )
        )
    }
}