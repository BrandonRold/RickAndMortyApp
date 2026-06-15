package com.brs.rickyandmorthy.presentation.feature.character.list.mapper

import com.brs.rickyandmorthy.domain.model.CharacterRM
import com.brs.rickyandmorthy.presentation.feature.character.list.component.state.StatusCharacter
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

fun CharacterRM.toUi() = CharacterUi(
    id = id,
    name = name,
    origin = originName,
    lastSeen = locationName,
    status = StatusCharacter.from(status),
    species = species,
    image = image
)

fun CharacterUi.toDomain(): CharacterRM = CharacterRM(
    id = id,
    name = name,
    status = status.name.lowercase(),
    species = species,
    gender = "",
    image = image,
    originName = origin,
    locationName = lastSeen
)
