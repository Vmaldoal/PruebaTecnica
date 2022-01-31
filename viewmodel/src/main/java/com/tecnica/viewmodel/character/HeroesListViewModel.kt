package com.tecnica.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecnica.domaincontroller.character.GetCharacterList
import com.tecnica.viewmodel.character.model.Mapper
import com.tecnica.viewmodel.character.model.CharacterView
import com.tecnica.viewmodel.manager.ExceptionType
import com.tecnica.viewmodel.manager.ResponseState
import com.tecnica.viewmodel.manager.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class HeroesListViewModel(private val getCharacterList: GetCharacterList): ViewModel() {

    val liveData = SingleLiveEvent<ResponseState<ArrayList<CharacterView>>>()

    fun getListCharacters(page: Int) {
        viewModelScope.launch {
            liveData.value = ResponseState.Loading
            getCharacterList.getCharacterList(page).fold(
                onSuccess = { characters ->
                    liveData.value = ResponseState.Success(ArrayList(characters.map {
                        Mapper.mapToView(
                            it
                        )
                    }))
                },
                onFailure = {
                    when (it) {
                        is UnknownHostException -> {
                            liveData.value = ResponseState.Error(ExceptionType.UnknownHostException)
                        }
                        else -> {
                            liveData.value = ResponseState.Error(ExceptionType.GenericException)
                        }
                    }
                }
            )
        }
    }
}