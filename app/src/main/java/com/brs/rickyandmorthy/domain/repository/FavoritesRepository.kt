package com.brs.rickyandmorthy.domain.repository

import com.brs.rickyandmorthy.domain.model.CharacterRM
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun addFavorite(character: CharacterRM)
    suspend fun removeFavorite(character: CharacterRM)
    suspend fun removeFavoriteById(id: Int)
    fun getFavorites(): Flow<List<CharacterRM>>
    fun searchFavorites(query: String): Flow<List<CharacterRM>>
    suspend fun isFavorite(id: Int): Boolean
}