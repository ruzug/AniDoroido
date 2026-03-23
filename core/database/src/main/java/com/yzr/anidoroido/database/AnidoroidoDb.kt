package com.yzr.anidoroido.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yzr.anidoroido.database.dao.AnimeDao
import com.yzr.anidoroido.database.model.AnimeEntity

@Database(
    entities = [
        AnimeEntity::class,
    ],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
internal abstract class AnidoroidoDb : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}