package com.brs.rickyandmorthy.core

import com.brs.rickyandmorthy.data.remote.dto.character.CharacterDetailDto
import com.brs.rickyandmorthy.data.remote.dto.character.CharactersResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    // 1) Obtener lista completa (paginada por la API)
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null
    ): CharactersResponseDto

    // 2) Buscar por nombre y status (ejemplo: name=rick&status=alive)
    @GET("character")
    suspend fun searchCharacters(
        @Query("name") name: String,
        @Query("status") status: String? = null,
        @Query("page") page: Int? = null
    ): CharactersResponseDto

    // 3) Detalle por id
    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int
    ): CharacterDetailDto

}