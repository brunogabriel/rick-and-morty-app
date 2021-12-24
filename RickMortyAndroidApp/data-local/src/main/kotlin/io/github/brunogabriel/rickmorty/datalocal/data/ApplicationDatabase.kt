package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.brunogabriel.rickmorty.datalocal.data.converters.StringConverters
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity

@Database(
    entities = [CharacterEntity::class, LocationEntity::class],
    version = 1
)
@TypeConverters(StringConverters::class)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun LocationDao(): LocationDao
}