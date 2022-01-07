package io.github.brunogabriel.rickmorty.datalocal.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.github.brunogabriel.rickmorty.datalocal.data.ApplicationDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

open class DataBaseTesting  {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: ApplicationDatabase

    @Before
    open fun setUp() {
        database = createInMemoryDatabase()
    }

    @After
    @Throws(IOException::class)
    open fun tearDown() {
        database.close()
    }

    private fun createInMemoryDatabase() = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        ApplicationDatabase::class.java
    ).allowMainThreadQueries().build()
}