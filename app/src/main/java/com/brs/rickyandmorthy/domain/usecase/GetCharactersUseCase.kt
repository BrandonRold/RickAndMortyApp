package com.brs.rickyandmorthy.domain.usecase

import androidx.paging.PagingData
import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(query: String? = null): Flow<PagingData<CharacterRM>> =
        repository.getCharactersPaging(query)

    suspend fun getList(page: Int? = null): Result<List<CharacterRM>> =
        repository.getCharacters(page)
}
