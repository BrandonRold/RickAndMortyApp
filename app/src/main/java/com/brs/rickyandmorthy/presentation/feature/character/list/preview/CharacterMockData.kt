package com.brs.rickyandmorthy.presentation.feature.character.list.preview

import com.brs.rickyandmorthy.presentation.feature.character.list.component.state.StatusCharacter
import com.brs.rickyandmorthy.presentation.feature.character.list.model.CharacterUi

val mockCharacters = listOf(
    CharacterUi(
        id = 1,
        name = "Rick Sanchez",
        status = StatusCharacter.ALIVE,
        species = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        origin = "Earth (C-137)",
        lastSeen = "Citadel of Ricks"
    ),
    CharacterUi(
        id = 2,
        name = "Morty Smith",
        status =StatusCharacter.ALIVE,
        species = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        origin = "Earth (C-137)",
        lastSeen = "Earth (Replacement Dimension)"
    ),
    CharacterUi(
        id = 3,
        name = "Summer Smith",
        status =StatusCharacter.ALIVE,
        species = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
        origin = "Earth (C-137)",
        lastSeen = "Earth (Replacement Dimension)"
    ),
    CharacterUi(
        id = 4,
        name = "Beth Smith",
        status = StatusCharacter.UNKNOWN,
        species = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
        origin = "Earth (C-137)",
        lastSeen = "Earth (Replacement Dimension)"
    ),
    CharacterUi(
        id = 5,
        name = "Jerry Smith",
        status = StatusCharacter.DEAD,
        species = "Human",
        image = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
        origin = "Earth (C-137)",
        lastSeen = "Earth (Replacement Dimension)"
    )
)
