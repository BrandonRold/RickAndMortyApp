package com.brs.rickyandmorthy.domain.model

data class CharacterRM(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val originName: String,
    val locationName: String
)
