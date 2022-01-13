package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity
import io.github.brunogabriel.rickmorty.datalocal.testing.DataBaseTesting
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class LocationDaoTest : DataBaseTesting() {

    private lateinit var dao: LocationDao

    @Before
    override fun setUp() {
        super.setUp()
        dao = database.locationDao()
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun shouldGetEntitiesByPaginationWay() = runBlocking {
        // given
        val entities = (1..100).map { createEntity(it) }
        dao.insertAll(entities)

        // then
        assertThat(dao.getEntities(limit = 100, offset = 0))
            .isEqualTo(entities)

        assertThat(dao.getEntities(limit = 10, offset = 0))
            .isEqualTo(entities.take(10))

        assertThat(dao.getEntities(limit = 10, offset = 10))
            .isEqualTo(entities.dropLast(80).takeLast(10))

        assertThat(dao.getEntities(limit = 10, offset = 100))
            .isEmpty()
    }

    private fun createEntity(id: Int) =
        LocationEntity(
            id = id,
            name = "name$id",
            type = "type$id",
            dimension = "dimension$id",
            residents = (0..id).map { it.toString() }
        )
}