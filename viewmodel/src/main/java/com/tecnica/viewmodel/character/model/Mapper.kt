package com.tecnica.viewmodel.character.model

import com.tecnica.domaincontroller.character.model.CharacterModel

class Mapper {
    companion object {
        fun mapToView(characterModel: CharacterModel): CharacterView {
            return CharacterView(
                id = characterModel.id,
                name = characterModel.name,
                description = characterModel.description,
                thumbnail = characterModel.thumbnail
            )
        }
    }
}