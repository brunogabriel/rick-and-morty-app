package io.github.brunogabriel.rickmorty.datalocal.data.converters

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class StringConvertersTest {
    private lateinit var converter: StringConverters

    @Before
    fun setUp() {
        converter = StringConverters()
    }

    @Test
    fun `should convert fromList`() {
        // given
        val input = listOf("first", "second")
        val expected = "[\"first\",\"second\"]"

        // when
        val result = converter.fromList(input)

        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should convert toList`() {
        // given
        val input = "[\"first\",\"second\"]"

        // when
        val result = converter.toList(input)

        // then
        assertThat(result).containsExactly("first", "second")
            .inOrder()
    }
}