package com.example.e_commerceapp.transaction_list.domain.repository

import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.model.BaseDomainModel

interface TransactionListRepository {
    suspend fun getTransactionList(
        page: Int,
        limit: Int
    ): ApiResultStatus<BaseDomainModel?>
}