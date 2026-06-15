package com.brs.rickyandmorthy.presentation.feature.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.usecase.AddFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.GetCharactersUseCase
import com.brs.rickyandmorthy.domain.usecase.IsFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.SearchCharactersUseCase
import com.brs.rickyandmorthy.presentation.feature.character.list.mapper.toUi
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
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

    private val _uiState = MutableStateFlow(CharactersListUiState())
    val uiState: StateFlow<CharactersListUiState> = _uiState

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val charactersPagingData: Flow<PagingData<CharacterUi>> = _searchQuery
        .flatMapLatest { query ->
            getCharacters(query)
        }
        .map { pagingData ->
            pagingData.map { it.toUi() }
        }
        .cachedIn(viewModelScope)

    fun search(name: String, status: String? = null) {
        _searchQuery.value = name
        _uiState.update { it.copy(searchQuery = name) }
    }

    fun setQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

    }

    fun toggleFavorite(characterUi: CharacterUi) {
        viewModelScope.launch {
            val fav = isFavorite(characterUi.id)
            if (!fav) {
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
            }
        }
    }
}
