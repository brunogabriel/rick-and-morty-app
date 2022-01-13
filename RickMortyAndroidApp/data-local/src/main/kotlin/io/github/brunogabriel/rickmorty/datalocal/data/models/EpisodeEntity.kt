package io.github.brunogabriel.rickmorty.datalocal.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String
)