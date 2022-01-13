package io.github.brunogabriel.rickmorty.main.episodes.data

import com.google.common.truth.Truth
import io.github.brunogabriel.rickmorty.datalocal.data.EpisodeDao
import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepositoryImpl
import io.github.brunogabriel.rickmorty.main.episodes.data.mapper.episodeResponseToEntity
import io.github.brunogabriel.rickmorty.main.episodes.data.models.EpisodeResponse
import io.github.brunogabriel.rickmorty.main.episodes.data.service.EpisodesService
import io.github.brunogabriel.rickmorty.shared.data.models.ListResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class EpisodeRepositoryTest {

    @MockK
    private lateinit var service: EpisodesService

    @RelaxedMockK
    private lateinit var dao: EpisodeDao

    private lateinit var repository: EpisodesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.rickmorty.main.episodes.data.mapper.EpisodeResponseMapperKt")
        repository = EpisodeRepositoryImpl(service, dao)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should get episodes from service when is forced to do it`() = runBlocking {
        // given
        val entity = mockk<EpisodeEntity>()
        val response = mockk<EpisodeResponse>()

        every { episodeResponseToEntity(response) } returns entity
        coEvery { dao.insertAll(listOf(entity)) } just runs
        coEvery { service.getEpisodes(1) } returns ListResponse(listOf(response))

        // when
        val result = repository.getEpisodes(1, true)

        // then
        Truth.assertThat(result).isEqualTo(listOf(entity))
    }

    @Test
    fun `should get episodes from database when has cache`() = runBlocking {
        // given
        val entity = mockk<EpisodeEntity>()
        val response = mockk<EpisodeResponse>()

        every { episodeResponseToEntity(response) } returns entity
        coEvery { dao.getEntities(CharacterRepositoryImpl.PAGE_SIZE, 0) } returns listOf(entity)

        // when
        val result = repository.getEpisodes(1, false)

        // then
        Truth.assertThat(result).isEqualTo(listOf(entity))
    }

    @Test
    fun `should get episodes from a service when has not cache`() = runBlocking {
        // given
        val entity = mockk<EpisodeEntity>()
        val response = mockk<EpisodeResponse>()

        every { episodeResponseToEntity(response) } returns entity
        coEvery { dao.getEntities(CharacterRepositoryImpl.PAGE_SIZE, 0) } returns emptyList()
        coEvery { dao.insertAll(listOf(entity)) } just runs
        coEvery { service.getEpisodes(1) } returns ListResponse(listOf(response))

        // when
        val result = repository.getEpisodes(1, false)

        // then
        Truth.assertThat(result).isEqualTo(listOf(entity))
    }
}