package com.brs.rickyandmorthy.data.remote.api

import com.brs.rickyandmorthy.data.remote.dto.character.CharacterDto

import com.brs.rickyandmorthy.data.remote.dto.character.CharactersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CharacterApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResponseDto

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDto
}
