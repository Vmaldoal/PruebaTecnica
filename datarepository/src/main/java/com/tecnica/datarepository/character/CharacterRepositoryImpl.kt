package com.tecnica.datarepository.character

import com.tecnica.datarepository.character.datasource.CharacterCacheDataSource
import com.tecnica.datarepository.character.datasource.CharacterRemoteDataSource
import com.tecnica.domaincontroller.character.model.CharacterModel
import com.tecnica.domaincontroller.character.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val characterRemote: CharacterRemoteDataSource,
    private val characterCacheDataSource: CharacterCacheDataSource
): CharacterRepository {

    companion object {
        private const val OFFSET: Int = 20
    }

    override suspend fun getCharacters(page: Int): Result<List<CharacterModel>> {
        return runCatching {
            characterRemote.getCharacters(page, OFFSET).map {
                Mapper.mapToDomain(it)
            }
        }
    }

    override suspend fun getCharacterById(id: Long): Result<CharacterModel> {
        return runCatching {
            getCharacter(id)
        }
    }

    private suspend fun getCharacter(id: Long): CharacterModel {
        characterCacheDataSource.getCharacterById(id)?.let {
            return Mapper.mapToDomain(it)
        }

        val result = characterRemote.getCharacterId(id)
        characterCacheDataSource.insertCharacter(result)
        return Mapper.mapToDomain(result)
    }

}