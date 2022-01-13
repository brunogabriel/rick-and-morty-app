package io.github.brunogabriel.rickmorty.main.characters.data.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.data.models.CharacterResponse
import org.junit.Test

class CharacterResponseMapperTest {

    @Test
    fun `should map response to entity`() {
        // given
        val response = CharacterResponse(
            id = 1,
            name = "name",
            status = "status",
            type = "type",
            gender = "gender",
            image = "image"
        )

        val entity = CharacterEntity(
            id = 1,
            name = "name",
            status = "status",
            type = "type",
            gender = "gender",
            image = "image"
        )

        // then
        assertThat(characterResponseToEntity(response)).isEqualTo(entity)
    }
}