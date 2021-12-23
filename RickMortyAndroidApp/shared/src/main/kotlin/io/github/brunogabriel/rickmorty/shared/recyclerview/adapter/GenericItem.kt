package io.github.brunogabriel.rickmorty.shared.recyclerview.adapter

sealed class GenericItem {
    data class View(val items: List<Any>) : GenericItem()
    object Loading : GenericItem()
    object TryAgain : GenericItem()
}