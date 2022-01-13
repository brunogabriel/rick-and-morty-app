package io.github.brunogabriel.rickmorty.main.locations.data

import com.google.common.truth.Truth
import io.github.brunogabriel.rickmorty.datalocal.data.LocationDao
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepositoryImpl
import io.github.brunogabriel.rickmorty.main.locations.data.mapper.locationResponseToEntity
import io.github.brunogabriel.rickmorty.main.locations.data.models.LocationResponse
import io.github.brunogabriel.rickmorty.main.locations.data.service.LocationService
import io.github.brunogabriel.rickmorty.shared.data.models.ListResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class LocationRepositoryTest {
    @MockK
    private lateinit var service: LocationService

    @RelaxedMockK
    private lateinit var dao: LocationDao

    private lateinit var repository: LocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.rickmorty.main.locations.data.mapper.LocationResponseMapperKt")
        repository = LocationRepositoryImpl(service, dao)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should get locations from service when is forced to do it`() = runBlocking {
        // given
        val entity = mockk<LocationEntity>()
        val response = mockk<LocationResponse>()

        every { locationResponseToEntity(response) } returns entity
        coEvery { dao.insertAll(listOf(entity)) } just runs
        coEvery { service.getLocations(1) } returns ListResponse(listOf(response))

        // when
        val result = repository.getLocations(1, true)

        // then
        Truth.assertThat(result).isEqualTo(listOf(entity))
    }

    @Test
    fun `should get locations from database when has cache`() = runBlocking {
        // given
        val entity = mockk<LocationEntity>()
        val response = mockk<LocationResponse>()

        every { locationResponseToEntity(response) } returns entity
        coEvery { dao.getEntities(CharacterRepositoryImpl.PAGE_SIZE, 0) } returns listOf(entity)

        // when
        val result = repository.getLocations(1, false)

        // then
        Truth.assertThat(result).isEqualTo(listOf(entity))
    }

    @Test
    fun `should get locations from a service when has not cache`() = runBlocking {
        // given
        val entity = mockk<LocationEntity>()
        val response = mockk<LocationResponse>()

        every { locationResponseToEntity(response) } returns entity
        coEvery { dao.getEntities(CharacterRepositoryImpl.PAGE_SIZE, 0) } returns emptyList()
        coEvery { dao.insertAll(listOf(entity)) } just runs
        coEvery { service.getLocations(1) } returns ListResponse(listOf(response))

        // when
        val result = repository.getLocations(1, false)

        // then
        Truth.assertThat(result).isEqualTo(listOf(entity))
    }
}