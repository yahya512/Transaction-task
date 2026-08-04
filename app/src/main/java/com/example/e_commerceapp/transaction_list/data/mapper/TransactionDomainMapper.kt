package com.example.e_commerceapp.transaction_list.data.mapper

import com.example.e_commerceapp.transaction_list.data.model.TransactionDto
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel

object TransactionDomainMapper {

    fun mapToDomain(data: TransactionDto?): TransactionDomainModel {
        return TransactionDomainModel(
            id = data?.id,
            reference = data?.reference,
            title = data?.title,
            icon = data?.icon,
            amount_label = data?.amount_label,
            status_label = data?.status_label,
            data_label = data?.date_label
        )

    }
}