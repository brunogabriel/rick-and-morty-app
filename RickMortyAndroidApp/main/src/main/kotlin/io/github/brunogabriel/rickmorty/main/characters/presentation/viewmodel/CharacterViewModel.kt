package io.github.brunogabriel.rickmorty.main.characters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.brunogabriel.rickmorty.main.characters.domain.CharactersUseCase
import io.github.brunogabriel.rickmorty.main.characters.domain.models.CharacterVO
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.coroutines.AppDispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val useCase: CharactersUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {
    private var page = 0

    val characterResult = MutableStateFlow<NetworkResult<List<CharacterVO>>>(
        NetworkResult.None
    )

    fun loadData() {
        viewModelScope.launch {
            characterResult.value = NetworkResult.Loading
            try {
                // TODO: Treat last page???
                val response = withContext(appDispatchers.io) {
                    useCase.fetchAll(page, false)
                }
                characterResult.value = NetworkResult.Success(response)
            } catch (exception: Exception) {
                characterResult.value = NetworkResult.Error(exception)
            }
        }
    }
}