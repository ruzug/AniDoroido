package com.yzr.anidoroido.network.demo

import com.yzr.anidoroido.common.AnidoroidoDispatcher
import com.yzr.anidoroido.common.AnidoroidoDispatcher.IO
import com.yzr.anidoroido.common.Dispatcher
import com.yzr.anidoroido.network.MediaNetworkDataSource
import com.yzr.anidoroido.network.model.MediaNetwork
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class DemoMediaNetworkDataSource @Inject constructor(
    @param:Dispatcher(IO) private val dispatcher: CoroutineDispatcher,
    private val networkJson : Json,
    private val assets: DemoAssetManager
): MediaNetworkDataSource {
    override suspend fun getAnimeList(): List<MediaNetwork> = getFromAssets("anime.json")

    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <reified T> getFromAssets(fileName: String): List<T> =
        withContext(dispatcher) {
            assets.open(fileName).use { inputStream ->
                networkJson.decodeFromStream(inputStream)
            }
        }
}