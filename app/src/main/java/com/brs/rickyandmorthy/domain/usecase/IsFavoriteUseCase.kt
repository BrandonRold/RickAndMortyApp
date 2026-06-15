package com.brs.rickyandmorthy.domain.usecase

import com.brs.rickyandmorthy.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(id: Int): Boolean = repository.isFavorite(id)
}