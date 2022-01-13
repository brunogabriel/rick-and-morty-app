package io.github.brunogabriel.rickmorty.main.characters.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.main.characters.domain.CharactersUseCase
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
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
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class CharacterViewModelTest {
    @get: Rule
    val rule = MainCoroutineTestRule()

    @MockK
    private lateinit var useCase: CharactersUseCase

    private lateinit var appDispatchers: AppDispatchers

    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setUp() {
        appDispatchers = AppDispatchers(
            rule.testDispatcher,
            rule.testDispatcher,
            rule.testDispatcher
        )
        MockKAnnotations.init(this)
        viewModel = CharacterViewModel(useCase, appDispatchers)
    }

    @After
    fun tearDown() {
        unmockkAll()
        Dispatchers.resetMain()
    }


    @Test
    fun `should load any page correctly when load data`() {
        // given
        val result = mutableListOf<NetworkResult<List<CharacterVO>>>()
        val charactersVo = listOf<CharacterVO>(mockk(), mockk())
        coEvery { useCase.fetchAll(1, false) } returns charactersVo

        rule.runTest {
            val testJob = launch { viewModel.characterResult.toList(result) }

            // when
            viewModel.loadData(1)

            // then
            assertThat(result).isEqualTo(
                listOf(
                    NetworkResult.None,
                    NetworkResult.Loading,
                    NetworkResult.Success(charactersVo)
                )
            )

            testJob.cancel()
        }
    }

    @Test
    fun `should got pagination exception when load data`() {
        // given
        val result = mutableListOf<NetworkResult<List<CharacterVO>>>()

        coEvery { useCase.fetchAll(1, false) } throws mockk<HttpException> {
            every { code() } returns 404
            every { message() } returns "AnyException"
        }

        rule.runTest {
            val testJob = launch { viewModel.characterResult.toList(result) }

            // when
            viewModel.loadData(1)

            // then
            assertThat(result).isEqualTo(
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
        val result = mutableListOf<NetworkResult<List<CharacterVO>>>()
        val exception =  ArithmeticException("Fail")
        coEvery { useCase.fetchAll(1, false) } throws exception

        rule.runTest {
            val testJob = launch { viewModel.characterResult.toList(result) }

            // when
            viewModel.loadData(1)

            // then
            assertThat(result).isEqualTo(
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