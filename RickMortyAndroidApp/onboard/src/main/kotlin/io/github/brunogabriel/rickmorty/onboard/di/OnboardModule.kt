package io.github.brunogabriel.rickmorty.onboard.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.rickmorty.onboard.presentation.activity.OnboardActivity
import io.github.brunogabriel.rickmorty.onboard.presentation.viewmodel.OnboardViewModel
import io.github.brunogabriel.rickmorty.shared.factory.ViewModelKey

@Module(
    includes = [
        OnboardActivityModule::class
    ]
)
abstract class OnboardModule

@Module(
    includes = [
        OnboardViewModelModule::class
    ]
)
internal abstract class OnboardActivityModule {
    @ContributesAndroidInjector
    abstract fun bindActivity(): OnboardActivity
}

@Module
internal abstract class OnboardViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(OnboardViewModel::class)
    abstract fun bindViewModel(viewModel: OnboardViewModel): ViewModel
}