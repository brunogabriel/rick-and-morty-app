package io.github.brunogabriel.rickmorty

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.brunogabriel.rickmorty.di.DaggerAppComponent

class RickMortyApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}