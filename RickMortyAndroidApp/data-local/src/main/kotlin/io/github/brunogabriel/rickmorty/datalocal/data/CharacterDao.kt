package io.github.brunogabriel.rickmorty.datalocal.data

import androidx.room.*
import io.github.brunogabriel.rickmorty.datalocal.data.models.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characterentity ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getEntities(
        limit: Int,
        offset: Int
    ): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<CharacterEntity>)

    @Delete
    fun delete(entity: CharacterEntity)

    @Update
    fun update(entity: CharacterEntity)
}