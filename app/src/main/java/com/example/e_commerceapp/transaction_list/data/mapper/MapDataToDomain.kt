package com.example.e_commerceapp.transaction_list.data.mapper

import com.example.e_commerceapp.transaction_list.data.model.TransactionsList
import com.example.e_commerceapp.transaction_list.domain.model.BaseDomainModel

object MapDataToDomain {
    fun mapToDomain(data: TransactionsList?): BaseDomainModel {
        val listOfTransactionDomain = TransactionListDomainMapper.mapListToDomain(data?.transactions)
        val paginationDomain = PaginationDomainMapper.mapToDomain(data?.pagination)

        return BaseDomainModel(
            transactions = listOfTransactionDomain,
            pagination = paginationDomain
        )
    }
}