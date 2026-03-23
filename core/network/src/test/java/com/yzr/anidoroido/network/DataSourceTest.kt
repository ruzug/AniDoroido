package com.yzr.anidoroido.network

import JvmUnitTestDemoAssetManager
import com.yzr.anidoroido.network.demo.DemoMediaNetworkDataSource
import com.yzr.anidoroido.network.model.MediaNetwork
import org.junit.Assert.*
import org.junit.Before
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DataSourceTest {
    private lateinit var subject: DemoMediaNetworkDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = DemoMediaNetworkDataSource(
            dispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestDemoAssetManager,
        )
    }

    @Test
    fun testDeserializationAnime() = runTest(testDispatcher) {
        assertEquals(
            MediaNetwork(
                media = MediaNetwork.Media(
                    id = 21404,
                    title = MediaNetwork.Title(
                        romaji = "Accel World: Infinite∞Burst",
                    ),
                    coverImage = MediaNetwork.CoverImage(
                        large = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx21404-2jkb1Gidfb18.jpg"
                    )
                )
            ),
            subject.getAnimeList().find { it.media?.id == 21404L }
        )
    }
}