package com.tecnica.domaincontroller.character

import com.tecnica.domaincontroller.character.model.CharacterModel

interface GetCharacterById {
    suspend fun getCharacterById(id: Long): Result<CharacterModel>
}