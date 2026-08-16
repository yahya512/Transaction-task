package com.example.e_commerceapp.transaction_details.domain.repository

import com.example.e_commerceapp.transaction_details.domain.model.TransactionDetailsDomainModel
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus

interface TransactionDetailsRepository {
    suspend fun getTransactionById(id: Int): ApiResultStatus<TransactionDetailsDomainModel>
}