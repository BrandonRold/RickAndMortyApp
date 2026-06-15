package com.brs.rickyandmorthy.domain.usecase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetCharactersUseCase(repository: com.brs.rickyandmorthy.domain.repository.CharacterRepository) =
        GetCharactersUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchCharactersUseCase(repository: com.brs.rickyandmorthy.domain.repository.CharacterRepository) =
        SearchCharactersUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCharacterDetailUseCase(repository: com.brs.rickyandmorthy.domain.repository.CharacterRepository) =
        GetCharacterDetailUseCase(repository)

    @Provides
    @Singleton
    fun provideAddFavoriteUseCase(repository: com.brs.rickyandmorthy.domain.repository.FavoritesRepository) =
        AddFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFavoriteUseCase(repository: com.brs.rickyandmorthy.domain.repository.FavoritesRepository) =
        RemoveFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(repository: com.brs.rickyandmorthy.domain.repository.FavoritesRepository) =
        GetFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchFavoritesUseCase(repository: com.brs.rickyandmorthy.domain.repository.FavoritesRepository) =
        SearchFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun provideIsFavoriteUseCase(repository: com.brs.rickyandmorthy.domain.repository.FavoritesRepository) =
        IsFavoriteUseCase(repository)
}