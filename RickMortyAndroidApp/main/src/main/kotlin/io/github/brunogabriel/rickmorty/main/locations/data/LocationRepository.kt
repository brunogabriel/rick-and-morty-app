package io.github.brunogabriel.rickmorty.main.locations.data

import androidx.annotation.VisibleForTesting
import io.github.brunogabriel.rickmorty.datalocal.data.LocationDao
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepositoryImpl
import io.github.brunogabriel.rickmorty.main.locations.data.mapper.locationResponseToEntity
import io.github.brunogabriel.rickmorty.main.locations.data.service.LocationService
import javax.inject.Inject

internal class LocationRepositoryImpl @Inject constructor(
    private val service: LocationService,
    private val locationDao: LocationDao
) : LocationRepository {

    override suspend fun getLocations(page: Int, forceRefresh: Boolean): List<LocationEntity> {
        return if (forceRefresh) {
            fetchFromService(page)
        } else {
            locationDao.getEntities(
                CharacterRepositoryImpl.PAGE_SIZE,
                (page - 1) * CharacterRepositoryImpl.PAGE_SIZE
            ).let {
                if (it.isEmpty()) {
                    fetchFromService(page)
                } else {
                    it
                }
            }
        }
    }

    private suspend fun fetchFromService(page: Int) =
        service.getLocations(page).results.map(::locationResponseToEntity).apply {
            locationDao.insertAll(this)
        }

    @VisibleForTesting
    internal companion object {
        const val PAGE_SIZE = 20
    }
}

interface LocationRepository {
    suspend fun getLocations(
        page: Int,
        forceRefresh: Boolean
    ): List<LocationEntity>
}