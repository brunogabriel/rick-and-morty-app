package io.github.brunogabriel.rickmorty.main.episodes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.brunogabriel.rickmorty.main.episodes.domain.EpisodesUseCase
import io.github.brunogabriel.rickmorty.main.episodes.domain.models.EpisodeVO
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.coroutines.AppDispatchers
import io.github.brunogabriel.rickmorty.shared.exception.PaginationException
import io.github.brunogabriel.rickmorty.shared.extensions.isNotFound
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class EpisodeViewModel @Inject constructor(
    private val useCase: EpisodesUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    val episodeResult = MutableStateFlow<NetworkResult<List<EpisodeVO>>>(
        NetworkResult.None
    )

    fun loadData(page: Int) {
        viewModelScope.launch {
            episodeResult.value = NetworkResult.Loading
            try {
                val response = withContext(appDispatchers.io) {
                    useCase.fetchAll(page, false)
                }
                episodeResult.value = NetworkResult.Success(response)
            } catch (exception: Exception) {
                val loadException =
                    if (exception is HttpException && exception.code().isNotFound()) {
                        PaginationException(exception.message(), exception.code())
                    } else {
                        exception
                    }
                episodeResult.value = NetworkResult.Error(loadException)
            }
        }
    }
}