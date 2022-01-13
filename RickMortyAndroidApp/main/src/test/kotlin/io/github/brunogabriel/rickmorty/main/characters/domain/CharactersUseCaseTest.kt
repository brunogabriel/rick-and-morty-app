package io.github.brunogabriel.rickmorty.main.characters.domain

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepository
import io.github.brunogabriel.rickmorty.main.characters.domain.mapper.characterEntityToVO
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharactersUseCaseTest {

    @MockK
    private lateinit var repository: CharacterRepository

    private lateinit var useCase: CharactersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.rickmorty.main.characters.domain.mapper.CharacterEntityMapperKt")
        useCase = CharactersUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should fetch all`() = runBlocking {
        // given
        val entity = mockk<CharacterEntity>()
        val vo = mockk<CharacterVO>()

        every { characterEntityToVO(entity) } returns vo
        coEvery { repository.getCharacters(1, false) } returns listOf(entity)

        // when
        val result = useCase.fetchAll(1, false)

        // then
        assertThat(result).isEqualTo(listOf(vo))

    }
}