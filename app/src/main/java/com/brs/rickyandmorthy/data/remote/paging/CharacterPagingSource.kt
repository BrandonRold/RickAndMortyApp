package com.brs.rickyandmorthy.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.brs.rickyandmorthy.core.RickAndMortyApi
import com.brs.rickyandmorthy.data.remote.mapper.toDomain
import com.brs.rickyandmorthy.domain.model.CharacterRM

class CharacterPagingSource(
    private val api: RickAndMortyApi,
    private val query: String? = null
) : PagingSource<Int, CharacterRM>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterRM> {
        val page = params.key ?: 1
        return try {
            val response = if (query.isNullOrEmpty()) {
                api.getCharacters(page)
            } else {
                api.searchCharacters(name = query, page = page)
            }

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.info.next != null) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterRM>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
