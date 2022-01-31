package com.tecnica.datarepository.core.network

import com.tecnica.datarepository.character.entities.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int): CharactersResponse

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Long
    ): CharactersResponse

}