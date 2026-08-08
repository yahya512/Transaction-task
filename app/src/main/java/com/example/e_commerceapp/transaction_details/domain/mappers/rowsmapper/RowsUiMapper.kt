package com.example.e_commerceapp.transaction_details.domain.mappers.rowsmapper

import com.example.e_commerceapp.transaction_details.domain.model.RowsDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.RowType
import com.example.e_commerceapp.transaction_details.presentation.model.RowsUiModel

object RowsUiMapper {
    fun mapToUi(data: RowsDomainModel): RowsUiModel {
        val resultType = when (data.key) {
            RowType.TYPE.type -> RowType.TYPE
            RowType.RECEIPT_NUMBER.type -> RowType.RECEIPT_NUMBER
            RowType.PAYMENT_METHOD.type -> RowType.PAYMENT_METHOD
            RowType.DATA.type -> RowType.DATA
            else -> {
                RowType.TYPE
            }
        }
        return RowsUiModel(
            key = resultType,
            label = data.label,
            value = data.value
        )
    }
}