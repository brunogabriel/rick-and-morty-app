package io.github.brunogabriel.rickmorty.main.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.brunogabriel.rickmorty.main.characters.domain.CharactersUseCase
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.coroutines.AppDispatchers
import io.github.brunogabriel.rickmorty.shared.exception.PaginationException
import io.github.brunogabriel.rickmorty.shared.extensions.isNotFound
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.http.HTTP
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val useCase: CharactersUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    val characterResult = MutableStateFlow<NetworkResult<List<CharacterVO>>>(
        NetworkResult.None
    )

    fun loadData(page: Int) {
        viewModelScope.launch {
            characterResult.value = NetworkResult.Loading
            try {
                val response = withContext(appDispatchers.io) {
                    useCase.fetchAll(page, false)
                }
                characterResult.value = NetworkResult.Success(response)
            } catch (exception: Exception) {
                val loadException =
                    if (exception is HttpException && exception.code().isNotFound()) {
                        PaginationException(exception.message(), exception.code())
                    } else {
                        exception
                    }
                characterResult.value = NetworkResult.Error(loadException)
            }
        }
    }
}