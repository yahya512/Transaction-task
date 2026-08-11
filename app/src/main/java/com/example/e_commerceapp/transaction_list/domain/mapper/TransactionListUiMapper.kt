package com.example.e_commerceapp.transaction_list.domain.mapper

import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.presentation.model.TransactionUiModel

object TransactionListUiMapper {
    fun matToUi(data: List<TransactionDomainModel>?): List<TransactionUiModel> {
        return data?.let {
            data.map { TransactionUiMapper.mapToUi(it) }
        } ?: emptyList()
    }
}