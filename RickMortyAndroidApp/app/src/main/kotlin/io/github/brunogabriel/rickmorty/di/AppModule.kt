package io.github.brunogabriel.rickmorty.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal abstract class AppModule {
    companion object {
        @Singleton
        @Provides
        fun providesContext(context: Application): Context = context
    }
}