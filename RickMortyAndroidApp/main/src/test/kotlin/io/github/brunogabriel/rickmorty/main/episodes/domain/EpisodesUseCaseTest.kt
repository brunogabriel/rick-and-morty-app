package io.github.brunogabriel.rickmorty.main.episodes.domain

import com.google.common.truth.Truth
import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.episodes.data.EpisodesRepository
import io.github.brunogabriel.rickmorty.main.episodes.domain.mapper.episodeEntityToVO
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class EpisodesUseCaseTest {
    @MockK
    private lateinit var repository: EpisodesRepository

    private lateinit var useCase: EpisodesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.rickmorty.main.episodes.domain.mapper.EpisodeEntityMapperKt")
        useCase = EpisodesUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should fetch all`() = runBlocking {
        // given
        val entity = mockk<EpisodeEntity>()
        val vo = mockk<EpisodeVO>()

        every { episodeEntityToVO(entity) } returns vo
        coEvery { repository.getEpisodes(1, false) } returns listOf(entity)

        // when
        val result = useCase.fetchAll(1, false)

        // then
        Truth.assertThat(result).isEqualTo(listOf(vo))

    }
}