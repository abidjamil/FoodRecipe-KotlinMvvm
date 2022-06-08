package com.example.foodrecipe.api

import com.example.foodrecipe.data.ErrorDescription

sealed class Result<T> {

    class Loading<T>(var showLoader: Boolean=false):Result<T>()
    class Success<T>(var data: T) : Result<T>()
    class Failure<E>(val e: ErrorDescription?) : Result<E>()
    class NetworkError<E>(val e: ErrorDescription?) : Result<E>()

}