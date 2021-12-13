package io.github.brunogabriel.rickmorty.main.characters.data.service

import io.github.brunogabriel.rickmorty.main.characters.data.models.CharactersListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersListResponse
}