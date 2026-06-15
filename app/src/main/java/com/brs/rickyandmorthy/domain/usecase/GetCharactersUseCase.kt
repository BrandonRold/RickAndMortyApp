package com.brs.rickyandmorthy.domain.usecase

import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int? = null): Result<List<CharacterRM>> =
        repository.getCharacters(page)
}