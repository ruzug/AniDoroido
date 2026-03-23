package com.yzr.anidoroido.data.di

import com.yzr.anidoroido.data.repository.AnimeRepository
import com.yzr.anidoroido.data.repository.OfflineFirstAnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindsAnimeRepository(
        animeRepository: OfflineFirstAnimeRepository
    ): AnimeRepository
}