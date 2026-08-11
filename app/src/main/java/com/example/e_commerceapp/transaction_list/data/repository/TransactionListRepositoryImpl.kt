package com.example.e_commerceapp.transaction_list.data.repository

import com.example.e_commerceapp.transaction_list.data.mapper.MapDataToDomain
import com.example.e_commerceapp.transaction_list.data.mapper.PaginationDomainMapper
import com.example.e_commerceapp.transaction_list.data.mapper.TransactionListDomainMapper
import com.example.e_commerceapp.transaction_list.data.remote.TransactionListApi
import com.example.e_commerceapp.transaction_list.data.remote.safeApiCall
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.model.BaseDomainModel
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.domain.repository.TransactionListRepository
import javax.inject.Inject

class TransactionListRepositoryImpl @Inject constructor(private val apiServices: TransactionListApi) :
    TransactionListRepository {
    override suspend fun getTransactionList(
        page: Int,
        limit: Int
    ): ApiResultStatus<BaseDomainModel?> {
        val apiStatus =
            safeApiCall { apiServices.getTransactionList(pageNumber = page, limit = limit) }
        return when (apiStatus) {
            is ApiResultStatus.Error -> {
                ApiResultStatus.Error(apiStatus.errorMessage)
            }

            is ApiResultStatus.Success -> {
                val baseDomainModel = MapDataToDomain.mapToDomain(apiStatus.data)
                ApiResultStatus.Success(baseDomainModel)
            }
        }
    }
}