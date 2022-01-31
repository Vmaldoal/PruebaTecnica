package com.tecnica.datarepository.character.datasource

import com.tecnica.datarepository.character.entities.MarvelCharacterResponse
import com.tecnica.datarepository.core.network.ApiServices

class CharacterRemoteDataSourceImpl(private val service: ApiServices): CharacterRemoteDataSource{
     override suspend fun getCharacterId(id: Long): MarvelCharacterResponse {
        return service.getCharacterById(id).dataResponse.results[0]
    }

    override suspend fun getCharacters(page: Int, offset: Int): List<MarvelCharacterResponse> {
        return service.getCharacters(page*offset).dataResponse.results
    }
}