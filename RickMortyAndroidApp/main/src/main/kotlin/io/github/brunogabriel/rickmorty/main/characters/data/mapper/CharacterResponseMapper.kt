package io.github.brunogabriel.rickmorty.main.characters.data.mapper

import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.data.models.CharacterResponse

fun characterResponseToEntity(
    response: CharacterResponse
) = CharacterEntity(
    id = response.id,
    name = response.name,
    status = response.status,
    type = response.type,
    gender = response.gender,
    image = response.image
)