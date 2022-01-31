package com.tecnica.datarepository.character.datasource

import com.tecnica.datarepository.character.entities.MarvelCharacterResponse

class CharacterCacheDataSourceImpl : CharacterCacheDataSource {

    private var localCharacter: MarvelCharacterResponse? = null

    override suspend fun getCharacterById(characterId: Long): MarvelCharacterResponse? {
        var charactersResponse: MarvelCharacterResponse? = null

        localCharacter?.let {
            if (it.id == characterId) {
                charactersResponse = it
            }
        }

        return charactersResponse
    }

    override fun insertCharacter(character: MarvelCharacterResponse) {
        localCharacter = character
    }
}