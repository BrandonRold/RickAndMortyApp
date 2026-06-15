package com.brs.rickyandmorthy.presentation.feature.character.list

import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.usecase.AddFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.GetCharactersUseCase
import com.brs.rickyandmorthy.domain.usecase.IsFavoriteUseCase
import com.brs.rickyandmorthy.domain.usecase.SearchCharactersUseCase
import com.brs.rickyandmorthy.presentation.feature.character.list.component.state.StatusCharacter
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersListViewModelTest {

    private lateinit var viewModel: CharactersListViewModel
    private val getCharactersUseCase: GetCharactersUseCase = mockk()
    private val searchCharactersUseCase: SearchCharactersUseCase = mockk()
    private val addFavoriteUseCase: AddFavoriteUseCase = mockk()
    private val isFavoriteUseCase: IsFavoriteUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CharactersListViewModel(
            getCharacters = getCharactersUseCase,
            searchCharacters = searchCharactersUseCase,
            addFavorite = addFavoriteUseCase,
            isFavorite = isFavoriteUseCase,
            skipInitialLoad = true
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadCharacters success updates uiState correctly`() = runTest {
        // Given
        val characters = listOf(
            CharacterRM(1, "Rick", "Alive", "Human", "Male", "", "Earth", "Earth")
        )
        coEvery { getCharactersUseCase(any()) } returns Result.success(characters)

        // When
        viewModel.loadCharacters()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(1, state.characters.size)
        assertEquals("Rick", state.characters[0].name)
        assertNull(state.errorMessage)
    }

    @Test
    fun `loadCharacters error sets errorMessage and stops isLoading`() = runTest {
        // Given
        val errorMessage = "Network Error"
        coEvery { getCharactersUseCase(any()) } returns Result.failure(Exception(errorMessage))

        // When
        viewModel.loadCharacters()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(errorMessage, state.errorMessage)
        assertTrue(state.characters.isEmpty())
    }

    @Test
    fun `search success updates characters list`() = runTest {
        // Given
        val characters = listOf(
            CharacterRM(1, "Morty", "Alive", "Human", "Male", "", "Earth", "Earth")
        )
        coEvery { searchCharactersUseCase(any(), any()) } returns Result.success(characters)

        // When
        viewModel.search("Morty")
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(1, state.characters.size)
        assertEquals("Morty", state.characters[0].name)
    }

    @Test
    fun `toggleFavorite calls addFavorite when isFavorite is false`() = runTest {
        // Given
        val characterUi = CharacterUi(1, "Rick", "Earth", "Earth", StatusCharacter.ALIVE, "Human", "")
        coEvery { isFavoriteUseCase(1) } returns false
        coEvery { addFavoriteUseCase(any()) } returns Unit

        // When
        viewModel.toggleFavorite(characterUi)
        advanceUntilIdle()

        // Then
        coVerify { addFavoriteUseCase(any()) }
    }

    @Test
    fun `setQuery updates searchQuery in uiState`() = runTest {
        // Given
        val query = "Rick"

        // When
        viewModel.setQuery(query)

        // Then
        assertEquals(query, viewModel.uiState.value.searchQuery)
    }
}
