package com.brs.rickyandmorthy.presentation.feature.favorites

import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

data class FavoritesUiState(
    val favorites: List<CharacterUi> = emptyList(),
    val showConfirmDialog: Boolean = false,
    val pendingDelete: CharacterUi? = null
)