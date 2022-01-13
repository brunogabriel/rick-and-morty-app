package io.github.brunogabriel.rickmorty.main.locations.data.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.locations.data.models.LocationResponse
import org.junit.Test

class LocationResponseMapperTest {
    @Test
    fun `should map response to entity`() {
        assertThat(
            locationResponseToEntity(
                LocationResponse(
                    id = 1,
                    name = "name",
                    dimension = "dimension",
                    type = "type",
                    residents = emptyList()
                )
            )
        ).isEqualTo(
            LocationEntity(
                id = 1,
                name = "name",
                dimension = "dimension",
                type = "type",
                residents = emptyList()
            )
        )
    }
}