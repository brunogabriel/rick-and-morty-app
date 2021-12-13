package io.github.brunogabriel.rickmorty.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.brunogabriel.rickmorty.RickMortyApplication
import io.github.brunogabriel.rickmorty.datalocal.di.DataLocalModule
import io.github.brunogabriel.rickmorty.network.di.NetworkModule
import io.github.brunogabriel.rickmorty.shared.di.SharedModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FeatureModule::class,
        NetworkModule::class,
        DataLocalModule::class,
        SharedModule::class,
        FakeModule::class
    ]
)
interface AppComponent : AndroidInjector<RickMortyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}