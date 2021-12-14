package io.github.brunogabriel.rickmorty.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.rickmorty.RickMortySplashActivity

@Module
internal abstract class SplashModule {
    @ContributesAndroidInjector
    abstract fun bindsActivity(): RickMortySplashActivity
}