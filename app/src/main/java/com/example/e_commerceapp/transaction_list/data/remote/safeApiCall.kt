package com.example.e_commerceapp.transaction_list.data.remote

import com.example.e_commerceapp.transaction_list.data.model.BaseResponse
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> BaseResponse<T>): ApiResultStatus<T> {
    return try {
        val response = apiCall()
        if (response.data != null) {
            ApiResultStatus.Success(response.data)
        } else {
            ApiResultStatus.Error(
                response.message ?: "Unknown Error"
            ) // catch error message from backend
        }
    } catch (e: IOException) {
        ApiResultStatus.Error(e.message ?: "Connection Failed")
    } catch (e: HttpException) {
        ApiResultStatus.Error(e.message())
    } catch (e: Exception) {
        ApiResultStatus.Error(e.message ?: "UnKnown Error")
    }
}

