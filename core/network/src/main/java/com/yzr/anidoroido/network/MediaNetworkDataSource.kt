package com.yzr.anidoroido.network

import com.yzr.anidoroido.network.model.MediaNetwork

interface MediaNetworkDataSource {
    suspend fun getAnimeList(): List<MediaNetwork>
}