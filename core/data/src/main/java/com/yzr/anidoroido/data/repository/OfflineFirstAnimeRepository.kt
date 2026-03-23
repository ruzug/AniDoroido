package com.yzr.anidoroido.data.repository

import com.yzr.anidoroido.data.repository.AnimeRepository
import com.yzr.anidoroido.data.model.asEntity
import com.yzr.anidoroido.database.dao.AnimeDao
import com.yzr.anidoroido.database.model.AnimeEntity
import com.yzr.anidoroido.database.model.asExternalModel
import com.yzr.anidoroido.model.Anime
import com.yzr.anidoroido.network.MediaNetworkDataSource
import com.yzr.anidoroido.network.model.MediaNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class OfflineFirstAnimeRepository @Inject constructor (
    private val animeDao: AnimeDao,
    private val network: MediaNetworkDataSource,
) : AnimeRepository {
    override fun getAnime(): Flow<List<Anime>> {
        return animeDao.getAllAnimeList()
            .map { it.map(AnimeEntity::asExternalModel) }
    }

    override suspend fun syncAnimeList() {
        val entities = network.getAnimeList().map(MediaNetwork::asEntity)
        animeDao.upsertAnimeList(entities)
    }
}