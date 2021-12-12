package io.github.brunogabriel.rickmorty.di

import dagger.Module
import io.github.brunogabriel.rickmorty.main.di.MainModule

@Module(
    includes = [
        MainModule::class
    ]
)
internal abstract class FeatureModule