package io.github.brunogabriel.rickmorty.main.locations.domain.mapper

import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO

fun locationEntityToVO(
    entity: LocationEntity
) = LocationVO(
    id = entity.id,
    name = entity.name,
    type = entity.type,
    dimension = entity.dimension,
    residents = entity.residents
)