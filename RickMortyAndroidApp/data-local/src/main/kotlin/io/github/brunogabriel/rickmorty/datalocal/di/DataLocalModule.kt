package io.github.brunogabriel.rickmorty.datalocal.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.brunogabriel.rickmorty.datalocal.data.ApplicationDatabase
import io.github.brunogabriel.rickmorty.datalocal.data.CharacterDao
import io.github.brunogabriel.rickmorty.datalocal.data.LocationDao
import javax.inject.Singleton

@Module
abstract class DataLocalModule {
    companion object {
        @Singleton
        @Provides
        fun providesDatabase(context: Context) =
            Room.databaseBuilder(context, ApplicationDatabase::class.java, "app-database.db")
                .build()

        @Provides
        fun providesCharacterDao(database: ApplicationDatabase): CharacterDao =
            database.characterDao()

        @Provides
        fun providesLocationDao(database: ApplicationDatabase): LocationDao =
            database.LocationDao()
    }
}