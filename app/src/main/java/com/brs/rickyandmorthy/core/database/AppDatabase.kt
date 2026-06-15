package com.brs.rickyandmorthy.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brs.rickyandmorthy.data.local.dao.FavoriteDao
import com.brs.rickyandmorthy.data.local.entity.FavoriteCharacterEntity


@Database(
    entities = [FavoriteCharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

}