package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.room.*
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity
import io.github.brunogabriel.rickmorty.datalocal.data.models.LocationEntity

@Dao
interface LocationDao {

    @Query("SELECT * FROM locationentity ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getEntities(
        limit: Int,
        offset: Int
    ): List<LocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<LocationEntity>)

    @Delete
    fun delete(entity: LocationEntity)

    @Update
    fun update(entity: LocationEntity)
}