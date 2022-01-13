package io.github.brunogabriel.rickmorty.main.characters.data

import androidx.annotation.VisibleForTesting
import io.github.brunogabriel.rickmorty.datalocal.data.CharacterDao
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.data.mapper.characterResponseToEntity
import io.github.brunogabriel.rickmorty.main.characters.data.service.CharacterService
import javax.inject.Inject

internal class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterService,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun getCharacters(page: Int, forceRefresh: Boolean): List<CharacterEntity> {
        return if (forceRefresh) {
            fetchFromService(page)
        } else {
            characterDao.getEntities(PAGE_SIZE, (page - 1) * PAGE_SIZE).let {
                if (it.isEmpty()) {
                    fetchFromService(page)
                } else {
                    it
                }
            }
        }
    }

    private suspend fun fetchFromService(page: Int) =
        service.getCharacters(page).results.map(::characterResponseToEntity)
            .apply {
                characterDao.insertAll(this)
            }

    @VisibleForTesting
    internal companion object {
        const val PAGE_SIZE = 20
    }
}

interface CharacterRepository {
    suspend fun getCharacters(
        page: Int,
        forceRefresh: Boolean
    ): List<CharacterEntity>
}