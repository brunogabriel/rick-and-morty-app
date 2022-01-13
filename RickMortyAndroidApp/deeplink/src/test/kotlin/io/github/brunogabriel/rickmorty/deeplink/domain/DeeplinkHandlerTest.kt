package io.github.brunogabriel.rickmorty.deeplink.domain

import android.content.Intent
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeeplinkHandlerTest {
    private lateinit var deeplinkHandler: DeeplinkHandler
    private lateinit var processors: MutableSet<DeeplinkProcessor>

    @MockK
    private lateinit var processor: DeeplinkProcessor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        processors = mutableSetOf()
        processors.add(processor)
        deeplinkHandler = DeeplinkHandlerImpl(processors)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should process deeplink`() {
        // given
        val deeplink = "deeplink"
        val intent = mockk<Intent>()

        every { processor.matches(deeplink) } returns true
        every { processor.execute(deeplink) } returns intent

        // when
        val result = deeplinkHandler.process(deeplink)

        // then
        assertThat(result).isEqualTo(intent)
    }

    @Test
    fun `should not process deep link when it has not a match`() {
        // given
        val deeplink = "deeplink"
        every { processor.matches(deeplink) } returns false

        // when
        val result = deeplinkHandler.process(deeplink)

        // then
        assertThat(result).isNull()
    }

}