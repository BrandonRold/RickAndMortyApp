package com.brs.rickyandmorthy.domain.usecase

import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.CharacterRepository
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(
        name: String,
        status: String? = null,
        page: Int? = null)
        : Result<List<CharacterRM>> =
        repository.searchCharacters(name = name, status = status, page = page)
}