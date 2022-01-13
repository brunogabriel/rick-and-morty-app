package io.github.brunogabriel.rickmorty.network.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Chucker

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Logging