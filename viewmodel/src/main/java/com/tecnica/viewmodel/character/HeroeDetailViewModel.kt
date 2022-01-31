package com.tecnica.viewmodel.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecnica.domaincontroller.character.GetCharacterById
import com.tecnica.viewmodel.character.model.Mapper
import com.tecnica.viewmodel.character.model.CharacterView
import com.tecnica.viewmodel.manager.ExceptionType
import com.tecnica.viewmodel.manager.ResponseState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class HeroeDetailViewModel(private val getCharacterById: GetCharacterById): ViewModel() {

    val liveData = MutableLiveData<ResponseState<CharacterView>>()

    fun getCharacterId(id: Long) {
        viewModelScope.launch {
            liveData.value = ResponseState.Loading
            getCharacterById.getCharacterById(id).fold(
                onSuccess = {
                    liveData.value = ResponseState.Success(Mapper.mapToView(it))
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