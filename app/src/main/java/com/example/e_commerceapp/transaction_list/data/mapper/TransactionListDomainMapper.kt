package com.example.e_commerceapp.transaction_list.data.mapper

import com.example.e_commerceapp.transaction_list.data.model.TransactionDto
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel

object TransactionListDomainMapper {
    fun mapListToDomain(data: List<TransactionDto>): List<TransactionDomainModel> {
        return data.map { TransactionDomainMapper.mapToDomain(it) }

    }
}