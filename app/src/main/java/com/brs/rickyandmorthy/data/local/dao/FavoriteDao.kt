package com.brs.rickyandmorthy.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brs.rickyandmorthy.data.local.entity.FavoriteCharacterEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(character: FavoriteCharacterEntity)

    @Delete
    suspend fun deleteFavorite(character: FavoriteCharacterEntity)

    @Query("DELETE FROM favorite_characters WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM favorite_characters ORDER BY name ASC")
    fun getAllFavorites(): Flow<List<FavoriteCharacterEntity>>

    @Query("SELECT * FROM favorite_characters WHERE name LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchFavoritesByName(query: String): Flow<List<FavoriteCharacterEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_characters WHERE id = :id)")
    suspend fun exists(id: Int): Boolean


}