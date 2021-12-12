package io.github.brunogabriel.rickmorty.main.characters.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.rickmorty.main.characters.presentation.fragment.CharactersFragment

@Module(
    includes = [
        CharactersFragmentModule::class
    ]
)
internal abstract class CharactersModule

@Module(
    includes = [
        CharactersViewModelModule::class
    ]
)
internal abstract class CharactersFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindFragment(): CharactersFragment
}

@Module(
    includes = [
        CharactersDependenciesModule::class
    ]
)
internal abstract class CharactersViewModelModule {

}

@Module
internal abstract class CharactersDependenciesModule {

}