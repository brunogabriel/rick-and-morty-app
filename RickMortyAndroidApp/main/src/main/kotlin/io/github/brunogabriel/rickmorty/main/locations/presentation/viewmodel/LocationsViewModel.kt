package io.github.brunogabriel.rickmorty.main.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.brunogabriel.rickmorty.main.locations.domain.LocationUseCase
import io.github.brunogabriel.rickmorty.main.locations.domain.models.LocationVO
import io.github.brunogabriel.rickmorty.network.result.NetworkResult
import io.github.brunogabriel.rickmorty.shared.coroutines.AppDispatchers
import io.github.brunogabriel.rickmorty.shared.exception.PaginationException
import io.github.brunogabriel.rickmorty.shared.extensions.isNotFound
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val useCase: LocationUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {
    val locationResult = MutableStateFlow<NetworkResult<List<LocationVO>>>(NetworkResult.None)

    fun loadData(page: Int) {
        viewModelScope.launch {
            locationResult.value = NetworkResult.Loading
            try {
                val response = withContext(appDispatchers.io) {
                    useCase.fetchAll(page, false)
                }
                locationResult.value = NetworkResult.Success(response)
            } catch (exception: Exception) {
                val loadException =
                    if (exception is HttpException && exception.code().isNotFound()) {
                        PaginationException(exception.message(), exception.code())
                    } else {
                        exception
                    }
                locationResult.value = NetworkResult.Error(loadException)
            }
        }
    }
}