package com.example.e_commerceapp.transaction_list.data.repository

import com.example.e_commerceapp.transaction_list.data.mapper.TransactionListDomainMapper
import com.example.e_commerceapp.transaction_list.data.remote.TransactionListApi
import com.example.e_commerceapp.transaction_list.data.remote.safeApiCall
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.domain.repository.TransactionListRepository
import javax.inject.Inject

class TransactionListRepositoryImpl @Inject constructor(private val apiServices: TransactionListApi) :
    TransactionListRepository {
    override suspend fun getTransactionList(): ApiResultStatus<List<TransactionDomainModel>?> {
        val apiStatus = safeApiCall { apiServices.getTransactionList() }
        return when (apiStatus) {
            is ApiResultStatus.Error -> {
                ApiResultStatus.Error(apiStatus.errorMessage)
            }

            is ApiResultStatus.Success -> {
                val listOfTransactionDomain =
                    TransactionListDomainMapper.mapListToDomain(apiStatus.data)
                ApiResultStatus.Success(listOfTransactionDomain)
            }
        }
    }
}