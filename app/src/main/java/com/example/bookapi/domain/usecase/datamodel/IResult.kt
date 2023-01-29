package com.example.bookapi.domain.usecase.datamodel

sealed class IResult<T>(val status: Status,val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): IResult<T>(Status.SUCCESS,data)
    class Error<T>(message: String?): IResult<T>(Status.ERROR)
    class Loading<T>(val isLoading: Boolean = true): IResult<T>(Status.LOADING)
}
