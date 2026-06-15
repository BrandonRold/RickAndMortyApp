package com.brs.rickyandmorthy.data.remote

import com.brs.rickyandmorthy.core.RickAndMortyApi
import com.brs.rickyandmorthy.data.remote.mapper.toDomain
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.CharacterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCharacterRepository @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(page: Int?): Result<List<CharacterRM>> =
        runCatching {
            api.getCharacters(page).results.map { it.toDomain() }
        }

    override suspend fun getCharacterDetail(id: Int): Result<CharacterRM> =
        runCatching {
            api.getCharacterDetail(id).toDomain()
        }

    override suspend fun searchCharacters(
        name: String,
        status: String?,
        page: Int?
    ): Result<List<CharacterRM>> =
        runCatching {
            api.searchCharacters(name, status, page).results.map { it.toDomain() }
        }
}