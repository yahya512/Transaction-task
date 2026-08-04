package com.example.e_commerceapp.transaction_list.domain.mapper

import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule

object TransactionListUiMapper {
    fun matToDomain(data: List<TransactionDomainModel>?): List<TransactionUiModule>? {
        return data?.let {
            data.map { TransactionUiMapper.mapToUi(it) }
        }
    }
}