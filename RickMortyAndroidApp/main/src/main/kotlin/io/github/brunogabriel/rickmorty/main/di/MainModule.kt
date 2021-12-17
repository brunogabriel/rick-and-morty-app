package io.github.brunogabriel.rickmorty.main.di

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet
import io.github.brunogabriel.rickmorty.deeplink.domain.DeeplinkProcessor
import io.github.brunogabriel.rickmorty.main.characters.di.CharactersModule
import io.github.brunogabriel.rickmorty.main.deeplink.MainDeeplinkProcessor
import io.github.brunogabriel.rickmorty.main.presentation.activity.MainActivity
import javax.inject.Singleton

@Module(
    includes = [
        MainActivityModule::class,
        CharactersModule::class,
        MainDeeplinkModule::class
    ]
)
abstract class MainModule

@Module
internal abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity
}

@Module
internal abstract class MainDeeplinkModule {
    @Binds
    @IntoSet
    abstract fun bindsDeeplinkProcessor(processor: MainDeeplinkProcessor): DeeplinkProcessor
}