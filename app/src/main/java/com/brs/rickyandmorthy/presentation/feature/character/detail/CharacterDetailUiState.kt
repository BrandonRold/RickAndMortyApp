package com.brs.rickyandmorthy.presentation.feature.character.detail

import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

data class CharacterDetailUiState(
    val character: CharacterUi? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
