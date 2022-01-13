package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}