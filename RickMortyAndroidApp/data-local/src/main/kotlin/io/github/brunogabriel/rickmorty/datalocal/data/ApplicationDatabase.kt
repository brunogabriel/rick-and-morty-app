package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}