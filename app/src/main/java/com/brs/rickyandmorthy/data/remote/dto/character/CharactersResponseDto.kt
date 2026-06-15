package com.brs.rickyandmorthy.data.remote.dto.character

import com.squareup.moshi.JsonClass
import com.brs.rickyandmorthy.data.remote.dto.character.CharacterDto

@JsonClass(generateAdapter = true)
data class CharactersResponseDto(
     val info: PageInfoDto,
     val results: List<CharacterDto>
)
@JsonClass(generateAdapter = true)
data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
