package io.github.brunogabriel.rickmorty.main.characters.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepository
import io.github.brunogabriel.rickmorty.main.characters.data.CharacterRepositoryImpl
import io.github.brunogabriel.rickmorty.main.characters.data.service.CharacterService
import io.github.brunogabriel.rickmorty.main.characters.domain.CharactersUseCase
import io.github.brunogabriel.rickmorty.main.characters.domain.CharactersUseCaseImpl
import io.github.brunogabriel.rickmorty.main.characters.presentation.fragment.CharactersFragment
import io.github.brunogabriel.rickmorty.main.characters.presentation.viewmodel.CharacterViewModel
import io.github.brunogabriel.rickmorty.shared.factory.ViewModelKey
import retrofit2.Retrofit

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
    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    abstract fun bindViewModel(viewModel: CharacterViewModel): ViewModel
}

@Module
internal abstract class CharactersDependenciesModule {
    @Binds
    abstract fun bindUseCase(useCase: CharactersUseCaseImpl): CharactersUseCase

    @Binds
    abstract fun bindRepository(repository: CharacterRepositoryImpl): CharacterRepository

    companion object {
        @Provides
        fun providesService(retrofit: Retrofit): CharacterService =
            retrofit.create(CharacterService::class.java)
    }
}