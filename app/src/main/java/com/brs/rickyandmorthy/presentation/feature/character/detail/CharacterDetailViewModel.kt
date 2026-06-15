package com.brs.rickyandmorthy.presentation.feature.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.usecase.AddFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.GetCharacterDetailUseCase
import com.brs.rickyandmorthy.domain.usecase.IsFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.RemoveFavoriteUseCase
import com.brs.rickyandmorthy.presentation.feature.character.list.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getDetail: GetCharacterDetailUseCase,
    private val addFavorite: AddFavoriteUseCase,
    private val removeFavorite: RemoveFavoriteUseCase,
    private val isFavorite: IsFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState

    fun loadDetail(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result: Result<CharacterRM> = getDetail(id)

            result.onSuccess { rm ->
                val ui = rm.toUi()
                val fav = isFavorite(id)
                _uiState.update {
                    it.copy(character = ui, isFavorite = fav, isLoading = false)
                }
            }

            result.onFailure { error ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = error.message ?: "Error desconocido")
                }
            }
        }
    }

    fun addToFavorites() {
        viewModelScope.launch {
            _uiState.value.character?.let { ui ->
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
                addFavorite(domain)
                _uiState.update { it.copy(isFavorite = true) }
            }
        }
    }

    fun removeFromFavorites() {
        viewModelScope.launch {
            _uiState.value.character?.let { ui ->
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
                _uiState.update { it.copy(isFavorite = false) }
            }
        }
    }
}