package io.github.brunogabriel.rickmorty.main.characters.data.service

import io.github.brunogabriel.rickmorty.main.characters.data.models.CharacterResponse
import io.github.brunogabriel.rickmorty.shared.data.models.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): ListResponse<CharacterResponse>
}