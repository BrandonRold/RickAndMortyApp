package com.brs.rickyandmorthy.presentation.feature.character.list

import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

data class CharactersListUiState(
    val isLoading: Boolean = false,
    val characters: List<CharacterUi> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null,
    val favoritesMessage: String? = null
)
