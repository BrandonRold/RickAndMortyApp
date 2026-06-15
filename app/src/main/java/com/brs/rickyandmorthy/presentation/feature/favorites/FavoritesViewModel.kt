package com.brs.rickyandmorthy.presentation.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.usecase.GetFavoritesUseCase
import com.brs.rickyandmorthy.domain.usecase.RemoveFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.SearchFavoritesUseCase
import com.brs.rickyandmorthy.presentation.feature.character.list.mapper.toUi
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavorites: GetFavoritesUseCase,
    private val searchFavorites: SearchFavoritesUseCase,
    private val removeFavorite: RemoveFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState

    init { observeFavorites() }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavorites().collectLatest { domainList ->
                _uiState.update { it.copy(favorites = domainList.map { it.toUi() }) }
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                observeFavorites()
            } else {
                searchFavorites(query).collectLatest { domainList ->
                    _uiState.update { it.copy(favorites = domainList.map { it.toUi() }) }
                }
            }
        }
    }

    fun requestRemove(characterUi: CharacterUi) {
        _uiState.update { it.copy(showConfirmDialog = true, pendingDelete = characterUi) }
    }

    fun confirmRemove() {
        viewModelScope.launch {
            _uiState.value.pendingDelete?.let { ui ->
                val domain = CharacterRM(
                    id = ui.id,
                    name = ui.name,
                    status = ui.status.name.lowercase(),
                    species = ui.species,
                    gender = "",
                    image = ui.image,
                    originName = ui.origin,
                    locationName = ui.lastSeen
                )
                removeFavorite(domain)
            }
            _uiState.update { it.copy(showConfirmDialog = false, pendingDelete = null) }
        }
    }

    fun cancelRemove() {
        _uiState.update { it.copy(showConfirmDialog = false, pendingDelete = null) }
    }
}