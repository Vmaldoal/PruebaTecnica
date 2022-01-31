package com.tecnica.datarepository.character.datasource

import com.tecnica.datarepository.character.entities.MarvelCharacterResponse

interface CharacterRemoteDataSource {
    suspend fun getCharacterId(id: Long): MarvelCharacterResponse
    suspend fun getCharacters(page: Int, offset: Int): List<MarvelCharacterResponse>
}