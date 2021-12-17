package io.github.brunogabriel.rickmorty.deeplink.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkHandler
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkHandlerImpl
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkProcessor
import io.github.brunogabriel.rickmorty.deeplink.presentation.DeeplinkActivity
import javax.inject.Singleton

@Module(
    includes = [
        DeeplinkActivityModule::class
    ]
)
abstract class DeeplinkModule

@Module(
    includes = [
        DeeplinkDependenciesModule::class
    ]
)
internal abstract class DeeplinkActivityModule {
    @ContributesAndroidInjector
    abstract fun bindsActivity(): DeeplinkActivity
}

@Module
internal abstract class DeeplinkDependenciesModule {
    companion object {
        @Singleton
        @Provides
        fun providesDeeplinkHandler(processors: Set<@JvmSuppressWildcards DeeplinkProcessor>): DeeplinkHandler =
            DeeplinkHandlerImpl(processors)
    }
}