package io.github.brunogabriel.rickmorty.main.locations.data.service

import io.github.brunogabriel.rickmorty.main.locations.data.models.LocationResponse
import io.github.brunogabriel.rickmorty.shared.data.models.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("location")
    suspend fun getLocations(
        @Query("page") page: Int
    ): ListResponse<LocationResponse>
}