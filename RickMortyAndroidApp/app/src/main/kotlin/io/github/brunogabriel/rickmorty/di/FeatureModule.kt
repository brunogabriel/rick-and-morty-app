package io.github.brunogabriel.rickmorty.di

import dagger.Module
import io.github.brunogabriel.rickmorty.main.di.MainModule
import io.github.brunogabriel.rickmorty.onboard.di.OnboardModule

@Module(
    includes = [
        OnboardModule::class,
        MainModule::class
    ]
)
internal abstract class FeatureModule