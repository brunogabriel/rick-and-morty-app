package io.github.brunogabriel.rickmorty.main.locations.domain

import com.google.common.truth.Truth
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.locations.data.LocationRepository
import io.github.brunogabriel.rickmorty.main.locations.domain.mapper.locationEntityToVO
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class LocationUseCaseTest {
    @MockK
    private lateinit var repository: LocationRepository

    private lateinit var useCase: LocationUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.rickmorty.main.locations.domain.mapper.LocationEntityMapperKt")
        useCase = LocationUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should fetch all`() = runBlocking {
        // given
        val entity = mockk<LocationEntity>()
        val vo = mockk<LocationVO>()

        every { locationEntityToVO(entity) } returns vo
        coEvery { repository.getLocations(1, false) } returns listOf(entity)

        // when
        val result = useCase.fetchAll(1, false)

        // then
        Truth.assertThat(result).isEqualTo(listOf(vo))

    }
}