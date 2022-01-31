package com.tecnica.datarepository.character.datasource

import com.tecnica.datarepository.character.entities.MarvelCharacterResponse

interface CharacterCacheDataSource {
    suspend fun getCharacterById(characterId: Long): MarvelCharacterResponse?
    fun insertCharacter(character: MarvelCharacterResponse)
}