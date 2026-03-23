package com.yzr.anidoroido.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.yzr.anidoroido.database.dao.AnimeDao
import org.junit.Before

internal abstract class DatabaseTest {
    private lateinit var db: AnidoroidoDb
    protected lateinit var animeDao: AnimeDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                AnidoroidoDb::class.java,
            ).build()
        }
        animeDao = db.animeDao()
    }
}