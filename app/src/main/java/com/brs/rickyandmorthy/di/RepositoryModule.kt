package com.brs.rickyandmorthy.di

import com.brs.rickyandmorthy.data.local.LocalFavoritesRepository
import com.brs.rickyandmorthy.data.remote.RemoteCharacterRepository
import com.brs.rickyandmorthy.domain.repository.CharacterRepository
import com.brs.rickyandmorthy.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        remote: RemoteCharacterRepository
    ): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        local: LocalFavoritesRepository
    ): FavoritesRepository
}