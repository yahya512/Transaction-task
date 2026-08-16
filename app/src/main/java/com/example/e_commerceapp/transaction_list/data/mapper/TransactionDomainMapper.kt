package com.example.e_commerceapp.transaction_list.data.mapper

import com.example.e_commerceapp.transaction_list.data.model.TransactionDto
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel

object TransactionDomainMapper {

    fun mapToDomain(data: TransactionDto): TransactionDomainModel {
        return TransactionDomainModel(
            id = data.id,
            reference = data.reference,
            title = data.title,
            icon = data.icon,
            amount_label = data.amountLabel,
            status_label = data.statusLabel,
            data_label = data.dateLabel,
            type = data.type
        )
    }
}