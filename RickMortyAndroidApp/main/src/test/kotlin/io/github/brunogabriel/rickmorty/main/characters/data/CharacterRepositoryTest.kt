package io.github.brunogabriel.rickmorty.main.characters.data

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.CharacterDao
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.data.mapper.characterResponseToEntity
import io.github.brunogabriel.rickmorty.main.characters.data.models.CharacterResponse
import io.github.brunogabriel.rickmorty.main.characters.data.service.CharacterService
import io.github.brunogabriel.rickmorty.shared.data.models.ListResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    @MockK
    private lateinit var service: CharacterService

    @RelaxedMockK
    private lateinit var characterDao: CharacterDao

    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.rickmorty.main.characters.data.mapper.CharacterResponseMapperKt")
        repository = CharacterRepositoryImpl(service, characterDao)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should get characters from service when is forced to do it`() = runBlocking {
        // given
        val entity = mockk<CharacterEntity>()
        val response = mockk<CharacterResponse>()

        every { characterResponseToEntity(response) } returns entity
        coEvery { characterDao.insertAll(listOf(entity)) } just runs
        coEvery { service.getCharacters(1) } returns ListResponse(listOf(response))

        // when
        val result = repository.getCharacters(1, true)

        // then
        assertThat(result).isEqualTo(listOf(entity))
    }

    @Test
    fun `should get characters from database when has cache`() = runBlocking {
        // given
        val entity = mockk<CharacterEntity>()
        val response = mockk<CharacterResponse>()

        every { characterResponseToEntity(response) } returns entity
        coEvery { characterDao.getEntities(CharacterRepositoryImpl.Companion.PAGE_SIZE, 0) } returns listOf(entity)

        // when
        val result = repository.getCharacters(1, false)

        // then
        assertThat(result).isEqualTo(listOf(entity))
    }

    @Test
    fun `should get characters from a service when has not cache`() = runBlocking {
        // given
        val entity = mockk<CharacterEntity>()
        val response = mockk<CharacterResponse>()

        every { characterResponseToEntity(response) } returns entity
        coEvery { characterDao.getEntities(CharacterRepositoryImpl.Companion.PAGE_SIZE, 0) } returns emptyList()
        coEvery { characterDao.insertAll(listOf(entity)) } just runs
        coEvery { service.getCharacters(1) } returns ListResponse(listOf(response))

        // when
        val result = repository.getCharacters(1, false)

        // then
        assertThat(result).isEqualTo(listOf(entity))
    }
}