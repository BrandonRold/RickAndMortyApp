package com.brs.rickyandmorthy.presentation.feature.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.usecase.AddFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.GetCharactersUseCase
import com.brs.rickyandmorthy.domain.usecase.IsFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.SearchCharactersUseCase
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi
import com.brs.rickyandmorthy.presentation.feature.character.list.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class CharactersListViewModel (
    private val getCharacters : GetCharactersUseCase,
    private val searchCharacters: SearchCharactersUseCase,
    private val addFavorite: AddFavoriteUseCase,
    private val isFavorite: IsFavoriteUseCase,
    private val skipInitialLoad: Boolean = false
) : ViewModel() {

    @Inject constructor(
        getCharacters : GetCharactersUseCase,
        searchCharacters: SearchCharactersUseCase,
        addFavorite: AddFavoriteUseCase,
        isFavorite: IsFavoriteUseCase
    ) : this(getCharacters, searchCharacters, addFavorite, isFavorite, false)

    protected val _uiState = MutableStateFlow(CharactersListUiState())
    val uiState: StateFlow<CharactersListUiState> = _uiState

    init {
        if (!skipInitialLoad) {
            loadCharacters()
        }
    }
    fun loadCharacters(page: Int? = null) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = getCharacters(page)

            result.onSuccess { rmList ->
                _uiState.update {
                    it.copy(
                        characters = rmList.map { rm -> rm.toUi() },
                        isLoading = false
                    )
                }
            }

            result.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Error desconocido"
                    )
                }
            }
        }
    }

    fun search(name: String, status: String? = null) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result: Result<List<CharacterRM>> =
                searchCharacters(name, status)

            result.onSuccess { rmList ->
                _uiState.update {
                    it.copy(
                        characters = rmList.map { rm -> rm.toUi() },
                        isLoading = false
                    )
                }
            }

            result.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Error desconocido"
                    )
                }
            }
        }
    }

    fun toggleFavorite(characterUi: CharacterUi) {
        viewModelScope.launch {
            val fav = isFavorite(characterUi.id)
            if (!fav) {
                // Construir domain minimal desde UI (ajustar si CharacterRM requiere más campos)
                val domain = CharacterRM(
                    id = characterUi.id,
                    name = characterUi.name,
                    status = characterUi.status.name.lowercase(),
                    species = characterUi.species,
                    gender = "",
                    image = characterUi.image,
                    originName = characterUi.origin,
                    locationName = characterUi.lastSeen
                )
                addFavorite(domain)
                // opcional: actualizar UI localmente si quieres feedback inmediato
            } else {
                // Para eliminar preferi hacerlo desde detalle o favoritos (confirmación)
            }
        }
    }

    fun setQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

}