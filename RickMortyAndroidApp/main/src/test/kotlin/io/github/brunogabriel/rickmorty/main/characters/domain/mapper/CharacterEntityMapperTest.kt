package io.github.brunogabriel.rickmorty.main.characters.domain.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import org.junit.Test

class CharacterEntityMapperTest {
    @Test
    fun `should map entity to vo`() {
        // given
        val entity = CharacterEntity(
            id = 1,
            name = "name",
            status = "status",
            type = "type",
            gender = "gender",
            image = "image"
        )

        val vo = CharacterVO(
            id = 1,
            name = "name",
            status = "status",
            type = "type",
            gender = "gender",
            image = "image"
        )

        // then
        assertThat(characterEntityToVO(entity)).isEqualTo(vo)
    }
}