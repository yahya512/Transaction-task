package com.example.e_commerceapp.transaction_details.data.remote

import com.example.e_commerceapp.transaction_details.data.model.TransactionDetailsResponse
import com.example.e_commerceapp.transaction_list.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionDetailsApi {
    @GET("test/transactions/{id}")
    suspend fun getTransactionDetailsByID(
        @Path("id") id: Int
    ): BaseResponse<TransactionDetailsResponse>
}