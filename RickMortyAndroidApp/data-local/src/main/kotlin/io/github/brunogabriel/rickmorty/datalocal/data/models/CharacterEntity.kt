package io.github.brunogabriel.rickmorty.datalocal.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val image: String
)