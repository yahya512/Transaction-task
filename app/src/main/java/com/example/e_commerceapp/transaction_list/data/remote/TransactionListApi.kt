package com.example.e_commerceapp.transaction_list.data.remote

import com.example.e_commerceapp.transaction_list.data.model.BaseResponse
import com.example.e_commerceapp.transaction_list.data.model.TransactionDto
import com.example.e_commerceapp.transaction_list.data.model.TransactionsList
import retrofit2.http.GET


interface TransactionListApi {
    @GET("test/transactions?layout=grouped")
    suspend fun getTransactionList(): BaseResponse<TransactionsList?>
}