package com.example.e_commerceapp.transaction_details.data.repository

import com.example.e_commerceapp.transaction_details.data.mappers.TransactionDetailsDomainMapper
import com.example.e_commerceapp.transaction_details.data.remote.TransactionDetailsApi
import com.example.e_commerceapp.transaction_details.domain.model.TransactionDetailsDomainModel
import com.example.e_commerceapp.transaction_details.domain.repository.TransactionDetailsRepository
import com.example.e_commerceapp.transaction_list.data.remote.safeApiCall
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import javax.inject.Inject

class TransactionDetailsRepositoryImpl @Inject constructor(private val apiService: TransactionDetailsApi) :
    TransactionDetailsRepository {
    override suspend fun getTransactionById(id: Int): ApiResultStatus<TransactionDetailsDomainModel> {
        val result = safeApiCall { apiService.getTransactionDetailsByID(id) }

        return when (result) {
            is ApiResultStatus.Success -> {
                val transactionDetailsDomain =
                    TransactionDetailsDomainMapper.mapToDomain(result.data.transaction)
                ApiResultStatus.Success(transactionDetailsDomain)
            }
            is ApiResultStatus.Error -> {
                ApiResultStatus.Error(result.errorMessage)
            }
        }
    }
}