package io.github.brunogabriel.rickmorty.main.deeplink

import android.content.Context
import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.deeplink.constants.DeeplinkConstants
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainDeeplinkProcessorTest {

    @RelaxedMockK
    private lateinit var context: Context
    private lateinit var processor: MainDeeplinkProcessor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        processor = MainDeeplinkProcessor(context)
    }

    @Test
    fun `should test match`() {
        // given
        val tests = listOf(
            Pair(DeeplinkConstants.MAIN, true),
            Pair("helloWorld?id=10", false),
        )
        // then
        tests.forEach { (test, result) ->
            assertThat(processor.matches(test)).isEqualTo(result)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}