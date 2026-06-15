package com.brs.rickyandmorthy.data.remote

import com.brs.rickyandmorthy.core.RickAndMortyApi
import com.brs.rickyandmorthy.data.remote.mapper.toDomain
import com.brs.rickyandmorthy.data.remote.paging.CharacterPagingSource
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteCharacterRepository @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override fun getCharactersPaging(query: String?): Flow<PagingData<CharacterRM>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSource(api, query) }
        ).flow
    }

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