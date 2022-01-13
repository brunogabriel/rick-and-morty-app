package io.github.brunogabriel.rickmorty.main.locations.domain.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO
import org.junit.Test

class LocationEntityMapperTest {
    @Test
    fun `should map entity to vo`() {
        assertThat(
            locationEntityToVO(
                LocationEntity(
                    id = 1,
                    name = "name",
                    dimension = "dimension",
                    type = "type",
                    residents = emptyList()
                )
            )
        ).isEqualTo(
            LocationVO(
                id = 1,
                name = "name",
                dimension = "dimension",
                type = "type",
                residents = emptyList()
            )
        )
    }
}