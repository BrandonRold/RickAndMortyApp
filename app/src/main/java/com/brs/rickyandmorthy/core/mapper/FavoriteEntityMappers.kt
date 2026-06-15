package com.brs.rickyandmorthy.core.mapper

import com.brs.rickyandmorthy.data.local.entity.FavoriteCharacterEntity
import com.brs.rickyandmorthy.domain.model.CharacterRM

fun FavoriteCharacterEntity.toDomain(): CharacterRM = CharacterRM(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image,
    originName = originName,
    locationName = locationName
)

fun CharacterRM.toEntity(): FavoriteCharacterEntity = FavoriteCharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image,
    originName = originName,
    locationName = locationName
)