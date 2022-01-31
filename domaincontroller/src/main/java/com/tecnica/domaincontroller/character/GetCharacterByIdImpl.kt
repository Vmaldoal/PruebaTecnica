package com.tecnica.domaincontroller.character

import com.tecnica.domaincontroller.character.model.CharacterModel
import com.tecnica.domaincontroller.character.repository.CharacterRepository

class GetCharacterByIdImpl(private val characterRepository: CharacterRepository): GetCharacterById {
    override suspend fun getCharacterById(id: Long): Result<CharacterModel> {
       return characterRepository.getCharacterById(id)
    }
}