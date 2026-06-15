package com.brs.rickyandmorthy.presentation.feature.character.list.model

import com.brs.rickyandmorthy.presentation.feature.character.list.component.state.StatusCharacter

data class CharacterUi(
    val id: Int,
    val name: String,
    var origin: String,
    var lastSeen: String,
    val status: StatusCharacter,
    val species: String,
    val image: String
)
