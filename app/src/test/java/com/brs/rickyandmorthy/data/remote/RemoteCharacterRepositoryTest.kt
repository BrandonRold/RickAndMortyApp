package com.brs.rickyandmorthy.data.remote

import com.brs.rickyandmorthy.core.RickAndMortyApi
import com.brs.rickyandmorthy.data.remote.dto.character.CharacterDto
import com.brs.rickyandmorthy.data.remote.dto.character.CharactersResponseDto
import com.brs.rickyandmorthy.data.remote.dto.character.PageInfoDto
import com.brs.rickyandmorthy.data.remote.dto.character.SimpleLocationDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RemoteCharacterRepositoryTest {

    private lateinit var repository: RemoteCharacterRepository
    private val api: RickAndMortyApi = mockk()

    @Before
    fun setUp() {
        repository = RemoteCharacterRepository(api)
    }

    @Test
    fun `getCharacters success returns list of domain characters`() = runTest {
        // Given
        val characterDto = CharacterDto(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = SimpleLocationDto("Earth", ""),
            location = SimpleLocationDto("Earth", ""),
            image = "",
            episode = emptyList(),
            url = "",
            created = ""
        )
        val response = CharactersResponseDto(
            info = PageInfoDto(1, 1, null, null),
            results = listOf(characterDto)
        )
        coEvery { api.getCharacters(any()) } returns response

        // When
        val result = repository.getCharacters(1)

        // Then
        assertTrue(result.isSuccess)
        val list = result.getOrNull()
        assertNotNull(list)
        assertEquals(1, list?.size)
        assertEquals("Rick", list?.get(0)?.name)
    }

    @Test
    fun `getCharacters failure returns failure result`() = runTest {
        // Given
        coEvery { api.getCharacters(any()) } throws Exception("API Error")

        // When
        val result = repository.getCharacters(1)

        // Then
        assertTrue(result.isFailure)
        assertEquals("API Error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `searchCharacters success returns filtered list`() = runTest {
        // Given
        val characterDto = CharacterDto(
            id = 2,
            name = "Morty",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = SimpleLocationDto("Earth", ""),
            location = SimpleLocationDto("Earth", ""),
            image = "",
            episode = emptyList(),
            url = "",
            created = ""
        )
        val response = CharactersResponseDto(
            info = PageInfoDto(1, 1, null, null),
            results = listOf(characterDto)
        )
        coEvery { api.searchCharacters("Morty", any(), any()) } returns response

        // When
        val result = repository.searchCharacters("Morty", null, 1)

        // Then
        assertTrue(result.isSuccess)
        assertEquals("Morty", result.getOrNull()?.get(0)?.name)
    }
}
