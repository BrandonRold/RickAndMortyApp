package com.brs.rickyandmorthy.domain.repository

import com.brs.rickyandmorthy.domain.model.CharacterRM

interface CharacterRepository {
    suspend fun getCharacters(page: Int?): Result<List<CharacterRM>>

    suspend fun getCharacterDetail(id: Int): Result<CharacterRM>

    suspend fun searchCharacters(
        name: String,
        status: String? = null,
        page: Int? = 1
    ): Result<List<CharacterRM>>
}