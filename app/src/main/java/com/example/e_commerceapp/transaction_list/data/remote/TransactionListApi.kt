package com.example.e_commerceapp.transaction_list.data.remote

import com.example.e_commerceapp.transaction_details.data.remote.GET_TRANSACTIONS_LIST_URL
import com.example.e_commerceapp.transaction_list.data.model.BaseResponse
import com.example.e_commerceapp.transaction_list.data.model.TransactionsList
import retrofit2.http.GET
import retrofit2.http.Query


interface TransactionListApi {
    @GET(GET_TRANSACTIONS_LIST_URL)
    suspend fun getTransactionList(
        @Query("per_page") limit: Int,
        @Query("page") pageNumber: Int
    ): BaseResponse<TransactionsList>
}