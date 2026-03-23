package com.yzr.anidoroido.database.di

import com.yzr.anidoroido.database.AnidoroidoDb
import com.yzr.anidoroido.database.dao.AnimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {
    @Provides
    fun provideAnimeDao(
        database: AnidoroidoDb
    ): AnimeDao = database.animeDao()
}