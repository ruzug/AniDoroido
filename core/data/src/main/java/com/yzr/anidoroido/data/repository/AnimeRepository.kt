package com.yzr.anidoroido.data.repository

import com.yzr.anidoroido.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getAnime(): Flow<List<Anime>>
    suspend fun syncAnimeList()
}