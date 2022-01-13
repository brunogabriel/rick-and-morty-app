package io.github.brunogabriel.rickmorty.main.characters.domain.mapper

import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO

fun characterEntityToVO(
    entity: CharacterEntity
) = CharacterVO(
    id = entity.id,
    name = entity.name,
    status = entity.status,
    type = entity.type,
    gender = entity.gender,
    image = entity.image
)