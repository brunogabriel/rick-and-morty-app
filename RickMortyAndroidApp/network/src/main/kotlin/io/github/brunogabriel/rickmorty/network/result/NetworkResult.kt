package io.github.brunogabriel.rickmorty.network.result

sealed class NetworkResult<out T> {
    object None : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val throwable: Throwable) : NetworkResult<Nothing>()
}