package com.brs.rickyandmorthy.domain.usecase

import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.FavoritesRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(character: CharacterRM) {
        repository.removeFavorite(character)
    }

    suspend fun byId(id: Int) {
        repository.removeFavoriteById(id)
    }
}