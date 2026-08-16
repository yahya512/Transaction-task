package com.example.e_commerceapp.transaction_list.domain.mapper

import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.presentation.model.TransactionUiModel
import com.example.e_commerceapp.transaction_list.presentation.model.ViewHolderType

object TransactionUiMapper {
    fun mapToUi(data: TransactionDomainModel?): TransactionUiModel {

        val resultType = when (data?.type) {
            ViewHolderType.TRANSACTIONS.type -> ViewHolderType.TRANSACTIONS
            else -> ViewHolderType.HEADER
        }

        return TransactionUiModel(
            id = data?.id,
            reference = data?.reference,
            title = data?.title,
            icon = data?.icon,
            amountLabel = data?.amountLabel,
            status = data?.status,
            statusLabel = data?.statusLabel,
            dataLabel = data?.dataLabel,
            type = resultType
        )
    }
}