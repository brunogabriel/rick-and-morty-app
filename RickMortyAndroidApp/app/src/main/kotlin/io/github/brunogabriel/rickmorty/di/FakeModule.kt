package io.github.brunogabriel.rickmorty.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.brunogabriel.rickmorty.FakeSplashActivity

@Module
internal abstract class FakeModule {
    @ContributesAndroidInjector
    abstract fun bindsActivity(): FakeSplashActivity
}