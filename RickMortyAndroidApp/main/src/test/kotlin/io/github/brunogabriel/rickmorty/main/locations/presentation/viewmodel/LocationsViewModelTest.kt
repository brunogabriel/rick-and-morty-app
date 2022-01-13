package io.github.brunogabriel.rickmorty.main.locations.presentation.viewmodel

import com.google.common.truth.Truth
import io.github.brunogabriel.rickmorty.main.locations.domain.LocationUseCase
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO
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
class LocationsViewModelTest {
    @get: Rule
    val rule = MainCoroutineTestRule()

    @MockK
    private lateinit var useCase: LocationUseCase

    private lateinit var appDispatchers: AppDispatchers

    private lateinit var viewModel: LocationsViewModel

    @Before
    fun setUp() {
        appDispatchers = AppDispatchers(
            rule.testDispatcher,
            rule.testDispatcher,
            rule.testDispatcher
        )
        MockKAnnotations.init(this)
        viewModel = LocationsViewModel(useCase, appDispatchers)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }


    @Test
    fun `should load any page correctly when load data`() {
        // given
        val result = mutableListOf<NetworkResult<List<LocationVO>>>()
        val vos = listOf<LocationVO>(mockk(), mockk())
        coEvery { useCase.fetchAll(1, false) } returns vos

        rule.runTest {
            val testJob = launch { viewModel.locationResult.toList(result) }

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
        val result = mutableListOf<NetworkResult<List<LocationVO>>>()

        coEvery { useCase.fetchAll(1, false) } throws mockk<HttpException> {
            every { code() } returns 404
            every { message() } returns "AnyException"
        }

        rule.runTest {
            val testJob = launch { viewModel.locationResult.toList(result) }

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
        val result = mutableListOf<NetworkResult<List<LocationVO>>>()
        val exception = ArithmeticException("Fail")
        coEvery { useCase.fetchAll(1, false) } throws exception

        rule.runTest {
            val testJob = launch { viewModel.locationResult.toList(result) }

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