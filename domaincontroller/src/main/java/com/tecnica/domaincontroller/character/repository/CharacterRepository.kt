package com.tecnica.domaincontroller.character.repository

import com.tecnica.domaincontroller.character.model.CharacterModel


interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<List<CharacterModel>>
    suspend fun getCharacterById(id: Long): Result<CharacterModel>
}