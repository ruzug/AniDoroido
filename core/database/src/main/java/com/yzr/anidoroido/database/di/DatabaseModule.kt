package com.yzr.anidoroido.database.di

import android.content.Context
import androidx.room.Room
import com.yzr.anidoroido.database.AnidoroidoDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideAnidoroidoDb(
        @ApplicationContext context: Context
    ): AnidoroidoDb = Room.databaseBuilder(
        context,
        AnidoroidoDb::class.java,
        "anidoroido-db"
    ).build()
}