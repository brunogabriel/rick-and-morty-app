package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.room.*
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.datalocal.data.models.EpisodeEntity
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episodeentity ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getEntities(
        limit: Int,
        offset: Int
    ): List<EpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<EpisodeEntity>)
}