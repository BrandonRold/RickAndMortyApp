package com.brs.rickyandmorthy.data.local

import com.brs.rickyandmorthy.core.mapper.toDomain
import com.brs.rickyandmorthy.data.local.dao.FavoriteDao
import com.brs.rickyandmorthy.data.local.mapper.toEntity
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalFavoritesRepository @Inject constructor(
        private val dao: FavoriteDao) : FavoritesRepository {
    override suspend fun addFavorite(character: CharacterRM) {
        dao.insertFavorite(character.toEntity())
    }

    override suspend fun removeFavorite(character: CharacterRM) {
        dao.deleteFavorite(character.toEntity())
    }

    override suspend fun removeFavoriteById(id: Int) {
        dao.deleteById(id)
    }

    override fun getFavorites(): Flow<List<CharacterRM>> =
        dao.getAllFavorites().map { list -> list.map { it.toDomain() } }

    override fun searchFavorites(query: String): Flow<List<CharacterRM>> =
        dao.searchFavoritesByName(query).map { list -> list.map { it.toDomain() } }

    override suspend fun isFavorite(id: Int): Boolean = dao.exists(id)
}