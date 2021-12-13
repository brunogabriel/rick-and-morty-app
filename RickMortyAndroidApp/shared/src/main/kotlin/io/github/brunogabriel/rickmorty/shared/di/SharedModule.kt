package io.github.brunogabriel.rickmorty.shared.di

import dagger.Module
import dagger.Provides
import io.github.brunogabriel.rickmorty.shared.coroutines.AppDispatchers
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes = [
        CoroutinesModule::class
    ]
)
abstract class SharedModule

@Module
internal abstract class CoroutinesModule {
    companion object {
        @Singleton
        @Provides
        fun providesAppDispatchers(): AppDispatchers = AppDispatchers(
            default = Dispatchers.Default,
            io = Dispatchers.IO,
            main = Dispatchers.Main
        )
    }
}