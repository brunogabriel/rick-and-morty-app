package io.github.brunogabriel.rickmorty.main.episodes.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.rickmorty.main.episodes.data.EpisodeRepositoryImpl
import io.github.brunogabriel.rickmorty.main.episodes.data.EpisodesRepository
import io.github.brunogabriel.rickmorty.main.episodes.data.service.EpisodesService
import io.github.brunogabriel.rickmorty.main.episodes.domain.EpisodesUseCase
import io.github.brunogabriel.rickmorty.main.episodes.domain.EpisodesUseCaseImpl
import io.github.brunogabriel.rickmorty.main.episodes.presentation.fragment.EpisodesFragment
import io.github.brunogabriel.rickmorty.main.episodes.presentation.viewmodel.EpisodeViewModel
import io.github.brunogabriel.rickmorty.shared.factory.ViewModelKey
import retrofit2.Retrofit

@Module(
    includes = [
        EpisodesFragmentModule::class
    ]
)
internal abstract class EpisodesModule

@Module(
    includes = [
        EpisodesViewModelModule::class
    ]
)
internal abstract class EpisodesFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindFragment(): EpisodesFragment
}

@Module(
    includes = [
        EpisodesDependenciesModule::class
    ]
)
internal abstract class EpisodesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EpisodeViewModel::class)
    abstract fun bindViewModel(viewModel: EpisodeViewModel): ViewModel
}

@Module
internal abstract class EpisodesDependenciesModule {
    @Binds
    abstract fun bindUseCase(useCase: EpisodesUseCaseImpl): EpisodesUseCase

    @Binds
    abstract fun bindRepository(repository: EpisodeRepositoryImpl): EpisodesRepository

    companion object {
        @Provides
        fun providesService(retrofit: Retrofit): EpisodesService =
            retrofit.create(EpisodesService::class.java)
    }
}