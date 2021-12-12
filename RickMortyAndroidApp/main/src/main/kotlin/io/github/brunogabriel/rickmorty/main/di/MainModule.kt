package io.github.brunogabriel.rickmorty.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.rickmorty.main.characters.di.CharactersModule
import io.github.brunogabriel.rickmorty.main.presentation.activity.MainActivity

@Module(
    includes = [
        MainActivityModule::class,
        CharactersModule::class
    ]
)
abstract class MainModule

@Module
internal abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindActivity(): MainActivity
}