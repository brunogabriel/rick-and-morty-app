package io.github.brunogabriel.rickmorty.main.characters.domain

import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepository
import io.github.brunogabriel.rickmorty.main.characters.domain.mapper.characterEntityToVO
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import javax.inject.Inject

interface CharactersUseCase {
    suspend fun fetchAll(page: Int, forceRefresh: Boolean): List<CharacterVO>
}

internal class CharactersUseCaseImpl @Inject constructor(
    private val repository: CharacterRepository
) : CharactersUseCase {
    override suspend fun fetchAll(page: Int, forceRefresh: Boolean): List<CharacterVO> {
        return repository.getCharacters(page, forceRefresh)
            .map(::characterEntityToVO)
    }
}