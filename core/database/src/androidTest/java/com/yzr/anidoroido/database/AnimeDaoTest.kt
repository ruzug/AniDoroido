package com.yzr.anidoroido.database

import com.yzr.anidoroido.database.model.AnimeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class AnimeDaoTest : DatabaseTest() {
    @Test
    fun getAnimeList() = runTest {
        insertAnime()

        val animeList = animeDao.getAllAnimeList().first()
        assert(3 == animeList.size)
    }

    private suspend fun insertAnime() {
        val animeEntities = listOf(
            testAnimeEntity(0, "Tengen Toppa Gurren Lagann"),
            testAnimeEntity(1, "Mirai Nikki"),
            testAnimeEntity(2, "Bleach")
        )

        animeDao.upsertAnimeList(animeEntities)
    }

    private fun testAnimeEntity(id: Long, name: String) = AnimeEntity(
        id = id,
        nameRomaji = name,
        imageLarge = "",
    )
}