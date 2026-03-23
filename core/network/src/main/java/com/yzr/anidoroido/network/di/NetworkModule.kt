package com.yzr.anidoroido.network.di

import android.content.Context
import com.yzr.anidoroido.network.MediaNetworkDataSource
import com.yzr.anidoroido.network.demo.DemoAssetManager
import com.yzr.anidoroido.network.demo.DemoMediaNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesDemoAssetManager(
        @ApplicationContext context: Context,
    ): DemoAssetManager = DemoAssetManager(context.assets::open)
}

@Module
@InstallIn(SingletonComponent::class)
internal interface BindNetworkModule {
    @Binds
    fun binds(impl: DemoMediaNetworkDataSource): MediaNetworkDataSource
}
