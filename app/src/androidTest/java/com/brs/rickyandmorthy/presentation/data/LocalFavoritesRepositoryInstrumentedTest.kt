package com.brs.rickyandmorthy.presentation.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.brs.rickyandmorthy.core.database.AppDatabase
import com.brs.rickyandmorthy.data.local.LocalFavoritesRepository
import com.brs.rickyandmorthy.data.local.dao.FavoriteDao
import com.brs.rickyandmorthy.data.local.entity.FavoriteCharacterEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class LocalFavoritesRepositoryInstrumentedTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: FavoriteDao
    private lateinit var repo: LocalFavoritesRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.favoriteDao()
        repo = LocalFavoritesRepository(dao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insert_and_get_favorites() = runBlocking {

        val entity = FavoriteCharacterEntity(
            id = 10,
            name = "Test",
            status = "alive",
            species = "Human",
            gender = "Male",
            image = "url",
            originName = "Origin",
            locationName = "Location"
        )

        dao.insertFavorite(entity)

        val list = repo.getFavorites().first()

        assertEquals(1, list.size)
        assertEquals("Test", list.first().name)
    }
}