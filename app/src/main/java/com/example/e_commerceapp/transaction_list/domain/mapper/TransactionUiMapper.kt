package com.example.e_commerceapp.transaction_list.domain.mapper

import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule

object TransactionUiMapper {
    fun mapToUi(data: TransactionDomainModel?): TransactionUiModule {
        return TransactionUiModule(
            id = data?.id,
            reference = data?.reference,
            title = data?.title,
            icon = data?.icon,
            amount_label = data?.amount_label,
            status_label = data?.status_label,
            data_label = data?.data_label
        )
    }
}