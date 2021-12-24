package io.github.brunogabriel.rickmorty.main.locations.data.mapper

import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.locations.data.models.LocationResponse

fun locationResponseToEntity(
    response: LocationResponse
) = LocationEntity(
    id = response.id,
    name = response.name,
    dimension = response.dimension,
    type = response.type,
    residents = response.residents
)
