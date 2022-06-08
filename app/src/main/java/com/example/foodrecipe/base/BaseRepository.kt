package com.example.foodrecipe.base

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.foodrecipe.api.Result
import com.example.foodrecipe.data.ErrorDescription
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException




abstract class BaseRepository<Response, Params> internal constructor() {

    abstract suspend fun fetchFromNetwork(params: Params): Response


    open fun start(
        params: Params,
        job: Job

    ): LiveData<Result<out Any?>> = liveData(Dispatchers.IO + job) {
        emit(Result.Loading(true))
        try {
            emit(Result.Success(fetchFromNetwork(params)))
        } catch (e: HttpException) {
            try {
                val jObjError = JSONObject( e.response()!!.errorBody()!!.string())
                emit(Result.NetworkError<Error>(ErrorDescription(jObjError.get("error").toString())))

            } catch (e: java.lang.Exception) {
                emit(Result.NetworkError<Error>(ErrorDescription(e.localizedMessage!!)))
            }

        } catch (e: UnknownHostException) {
            emit(Result.NetworkError<Error>(ErrorDescription(e.localizedMessage!!)))
        } catch (e: SocketTimeoutException) {
            emit(Result.NetworkError<Error>(ErrorDescription(e.localizedMessage!!)))
        } catch (ex: Exception) {
            emit(Result.Failure<Error>(ErrorDescription(ex.localizedMessage!!)))
        }
    }
}