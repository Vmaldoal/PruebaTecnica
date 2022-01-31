package com.tecnica.viewmodel.manager

sealed class ResponseState<out T> {
    object Loading : ResponseState<Nothing>()
    data class Error(val exceptiontype: ExceptionType) : ResponseState<Nothing>()
    data class Success<T>(val item: T) : ResponseState<T>()
}