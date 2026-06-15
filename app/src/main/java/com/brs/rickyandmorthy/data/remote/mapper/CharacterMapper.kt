package com.brs.rickyandmorthy.data.remote.mapper

import com.brs.rickyandmorthy.data.remote.dto.character.CharacterDetailDto
import com.brs.rickyandmorthy.data.remote.dto.character.CharacterDto
import com.brs.rickyandmorthy.domain.model.CharacterRM

fun CharacterDto.toDomain(): CharacterRM = CharacterRM(
    id = id,
    name = name,
    status = status ?: "unknown",
    species = species ?: "",
    gender = gender ?: "",
    image = image ?: "",
    originName = origin?.name ?: "",
    locationName = location?.name ?: ""
)

fun CharacterDetailDto.toDomain(): CharacterRM = CharacterRM(
    id = id,
    name = name,
    status = status ?: "unknown",
    species = species ?: "",
    gender = gender ?: "",
    image = image ?: "",
    originName = origin?.name ?: "",
    locationName = location?.name ?: ""
)

fun List<CharacterDto>.toDomain() = map { it.toDomain() }
