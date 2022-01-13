package io.github.brunogabriel.rickmorty.main.locations.domain

import io.github.brunogabriel.rickmorty.main.locations.data.LocationRepository
import io.github.brunogabriel.rickmorty.main.locations.domain.mapper.locationEntityToVO
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO
import javax.inject.Inject

interface LocationUseCase {
    suspend fun fetchAll(page: Int, forceRefresh: Boolean): List<LocationVO>
}

internal class LocationUseCaseImpl @Inject constructor(
    private val repository: LocationRepository
) : LocationUseCase {
    override suspend fun fetchAll(page: Int, forceRefresh: Boolean): List<LocationVO> {
        return repository.getLocations(page, forceRefresh)
            .map(::locationEntityToVO)
    }
}