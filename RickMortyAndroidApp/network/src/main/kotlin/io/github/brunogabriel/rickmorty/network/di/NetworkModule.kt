package io.github.brunogabriel.rickmorty.network.di

import dagger.Module

@Module(
    includes = [
        RetrofitModule::class
    ]
)
abstract class NetworkModule