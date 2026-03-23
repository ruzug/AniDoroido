package com.yzr.anidoroido.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.yzr.anidoroido.database.model.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Upsert
    suspend fun upsertAnimeList(animeList: List<AnimeEntity>)

    @Query(value = "SELECT * FROM anime")
    fun getAllAnimeList(): Flow<List<AnimeEntity>>
}