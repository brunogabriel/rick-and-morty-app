package io.github.brunogabriel.rickmorty.main.locations.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.rickmorty.main.locations.data.LocationRepository
import io.github.brunogabriel.rickmorty.main.locations.data.LocationRepositoryImpl
import io.github.brunogabriel.rickmorty.main.locations.data.service.LocationService
import io.github.brunogabriel.rickmorty.main.locations.domain.LocationUseCase
import io.github.brunogabriel.rickmorty.main.locations.domain.LocationUseCaseImpl
import io.github.brunogabriel.rickmorty.main.locations.presentation.fragment.LocationsFragment
import io.github.brunogabriel.rickmorty.main.locations.presentation.viewmodel.LocationsViewModel
import io.github.brunogabriel.rickmorty.shared.factory.ViewModelKey
import retrofit2.Retrofit


@Module(
    includes = [
        LocationsFragmentModule::class
    ]
)
internal abstract class LocationsModule

@Module(
    includes = [
        LocationsViewModelModule::class
    ]
)
internal abstract class LocationsFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindFragment(): LocationsFragment
}

@Module(
    includes = [
        LocationsDependenciesModule::class
    ]
)
internal abstract class LocationsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    abstract fun bindViewModel(viewModel: LocationsViewModel): ViewModel
}

@Module
internal abstract class LocationsDependenciesModule {
    @Binds
    abstract fun bindUseCase(useCase: LocationUseCaseImpl): LocationUseCase

    @Binds
    abstract fun bindRepository(repository: LocationRepositoryImpl): LocationRepository

    companion object {
        @Provides
        fun providesService(retrofit: Retrofit): LocationService =
            retrofit.create(LocationService::class.java)
    }
}