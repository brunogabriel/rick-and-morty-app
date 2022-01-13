package io.github.brunogabriel.rickmorty.main.episodes.presentation.viewmodel

import com.google.common.truth.Truth
import io.github.brunogabriel.rickmorty.main.episodes.domain.EpisodeSeasonBuilder
import io.github.brunogabriel.rickmorty.main.episodes.domain.EpisodesUseCase
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.coroutines.AppDispatchers
import io.github.brunogabriel.rickmorty.shared.exception.PaginationException
import io.github.brunogabriel.rickmorty.testing.MainCoroutineTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class EpisodeViewModelTest {
    @get: Rule
    val rule = MainCoroutineTestRule()

    @MockK
    private lateinit var useCase: EpisodesUseCase

    @MockK
    private lateinit var seasonBuilder: EpisodeSeasonBuilder

    private lateinit var appDispatchers: AppDispatchers

    private lateinit var viewModel: EpisodeViewModel

    @Before
    fun setUp() {
        appDispatchers = AppDispatchers(
            rule.testDispatcher,
            rule.testDispatcher,
            rule.testDispatcher
        )
        MockKAnnotations.init(this)
        viewModel = EpisodeViewModel(useCase, seasonBuilder, appDispatchers)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }


    @Test
    fun `should load any page correctly when load data`() {
        // given
        val result = mutableListOf<NetworkResult<List<Any>>>()
        val vos = listOf<EpisodeVO>(mockk(), mockk())
        every { seasonBuilder.addSeason(vos) } returns vos
        coEvery { useCase.fetchAll(1, false) } returns vos

        rule.runTest {
            val testJob = launch { viewModel.episodeResult.toList(result) }

            // when
            viewModel.loadData(1)

            // then
            Truth.assertThat(result).isEqualTo(
                listOf(
                    NetworkResult.None,
                    NetworkResult.Loading,
                    NetworkResult.Success(vos)
                )
            )

            testJob.cancel()
        }
    }

    @Test
    fun `should got pagination exception when load data`() {
        // given
        val result = mutableListOf<NetworkResult<List<Any>>>()

        coEvery { useCase.fetchAll(1, false) } throws mockk<HttpException> {
            every { code() } returns 404
            every { message() } returns "AnyException"
        }

        rule.runTest {
            val testJob = launch { viewModel.episodeResult.toList(result) }

            // when
            viewModel.loadData(1)

            // then
            Truth.assertThat(result).isEqualTo(
                listOf(
                    NetworkResult.None,
                    NetworkResult.Loading,
                    NetworkResult.Error(PaginationException("AnyException", 404))
                )
            )

            testJob.cancel()
        }
    }

    @Test
    fun `should got any exception when load data`() {
        // given
        val result = mutableListOf<NetworkResult<List<Any>>>()
        val exception = ArithmeticException("Fail")
        coEvery { useCase.fetchAll(1, false) } throws exception

        rule.runTest {
            val testJob = launch { viewModel.episodeResult.toList(result) }

            // when
            viewModel.loadData(1)

            // then
            Truth.assertThat(result).isEqualTo(
                listOf(
                    NetworkResult.None,
                    NetworkResult.Loading,
                    NetworkResult.Error(exception)
                )
            )

            testJob.cancel()
        }
    }
}