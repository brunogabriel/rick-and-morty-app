package io.github.brunogabriel.rickmorty.main.episodes.domain

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class EpisodeSeasonBuilderTest {
    private lateinit var seasonBuilder: EpisodeSeasonBuilder

    @Before
    fun setUp() {
        seasonBuilder = EpisodeSeasonBuilderImpl()
    }

    @Test
    fun `should add season`() {
        // given
        val s01e01 = mockk<EpisodeVO> { every { episode } returns "S01E01" }
        val s01e02 = mockk<EpisodeVO> { every { episode } returns "S01E02" }
        val s01e03 = mockk<EpisodeVO> { every { episode } returns "S01E03" }
        val s02e01 = mockk<EpisodeVO> { every { episode } returns "S02E01" }

        // when
        val result = seasonBuilder.addSeason(listOf(s01e01, s01e02, s01e03, s02e01))

        // then
        assertThat(result).isEqualTo(
            listOf(
                "Season 1",
                s01e01,
                s01e02,
                s01e03,
                "Season 2",
                s02e01
            )
        )
    }

    @Test
    fun `should not add season when there is a season`() {
        // given
        val s01e01 = mockk<EpisodeVO> { every { episode } returns "S01E01" }
        val s01e02 = mockk<EpisodeVO> { every { episode } returns "S01E02" }
        val s01e03 = mockk<EpisodeVO> { every { episode } returns "S01E03" }
        val newEpisode = mockk<EpisodeVO> { every { episode } returns "S01E04" }

        seasonBuilder.addSeason(listOf(s01e01, s01e02, s01e03))

        // when
        val result = seasonBuilder.addSeason(listOf(newEpisode))

        // then
        assertThat(result).isEqualTo(listOf(newEpisode))
    }
}