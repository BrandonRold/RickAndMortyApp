package com.brs.rickyandmorthy.data.local

import com.brs.rickyandmorthy.data.local.dao.FavoriteDao
import com.brs.rickyandmorthy.data.local.entity.FavoriteCharacterEntity
import com.brs.rickyandmorthy.domain.model.CharacterRM
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LocalFavoritesRepositoryTest {

    private lateinit var repository: LocalFavoritesRepository
    private val dao: FavoriteDao = mockk()

    @Before
    fun setUp() {
        repository = LocalFavoritesRepository(dao)
    }

    @Test
    fun `addFavorite calls dao insertFavorite`() = runTest {
        // Given
        val character = CharacterRM(1, "Rick", "Alive", "Human", "Male", "", "Earth", "Earth")
        coEvery { dao.insertFavorite(any()) } returns Unit

        // When
        repository.addFavorite(character)

        // Then
        coVerify { dao.insertFavorite(match { it.id == 1 && it.name == "Rick" }) }
    }

    @Test
    fun `getFavorites returns flow of domain models`() = runTest {
        // Given
        val entities = listOf(
            FavoriteCharacterEntity(1, "Rick", "Alive", "Human", "Male", "", "Earth", "Earth")
        )
        coEvery { dao.getAllFavorites() } returns flowOf(entities)

        // When
        val result = repository.getFavorites().first()

        // Then
        assertEquals(1, result.size)
        assertEquals("Rick", result[0].name)
    }

    @Test
    fun `isFavorite calls dao exists`() = runTest {
        // Given
        coEvery { dao.exists(1) } returns true

        // When
        val result = repository.isFavorite(1)

        // Then
        assertTrue(result)
        coVerify { dao.exists(1) }
    }
}
