package com.example.e_commerceapp.transaction_list.domain.repository

import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.model.BaseDomainModel
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel

interface TransactionListRepository {
    suspend fun getTransactionList(
        page: Int,
        limit: Int
    ): ApiResultStatus<BaseDomainModel?>
}