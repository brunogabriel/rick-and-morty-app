package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.datalocal.testing.DataBaseTesting
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class CharacterDaoTest : DataBaseTesting() {

    private lateinit var dao: CharacterDao

    @Before
    override fun setUp() {
        super.setUp()
        dao = database.characterDao()
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

    @Test
    fun shouldDeleteEntity() = runBlocking {
        // given
        val entities = listOf(createEntity(1))
        dao.insertAll(entities)

        // when
        assertThat(dao.getEntities(limit = 10, offset = 0))
            .isNotEmpty()

        dao.delete(entities.first())

        // then
        assertThat(dao.getEntities(limit = 10, offset = 0))
            .isEmpty()
    }

    @Test
    fun shouldUpdateEntity() = runBlocking {
        // given
        val entities = listOf(createEntity(1))
        val customEntity = entities.first().copy(name = "Bruno")
        dao.insertAll(entities)

        // when
        assertThat(dao.getEntities(limit = 10, offset = 0))
            .isNotEmpty()

        dao.update(customEntity)

        // then
        assertThat(dao.getEntities(limit = 10, offset = 0))
            .contains(customEntity)
    }

    private fun createEntity(id: Int) =
        CharacterEntity(
            id = id,
            name = "name$id",
            status = "status$id",
            type = "type$id",
            gender = "gender$id",
            image = "image$id"
        )
}