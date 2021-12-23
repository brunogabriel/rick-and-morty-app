package io.github.brunogabriel.rickmorty.shared.exception

data class PaginationException(override val message: String, val code: Int) : Exception(message)