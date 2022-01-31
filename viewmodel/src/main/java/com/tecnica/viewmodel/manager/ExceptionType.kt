package com.tecnica.viewmodel.manager

sealed class ExceptionType {
    object UnknownHostException: ExceptionType()
    object GenericException: ExceptionType()
}