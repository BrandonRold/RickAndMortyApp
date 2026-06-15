package com.brs.rickyandmorthy.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_characters")
data class FavoriteCharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "origin_name")
    val originName: String,

    @ColumnInfo(name = "location_name")
    val locationName: String
)
