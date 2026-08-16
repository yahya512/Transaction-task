package com.example.e_commerceapp.transaction_details.domain.mappers.rowsmapper

import com.example.e_commerceapp.transaction_details.domain.model.RowsDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.RowsUiModel

object RowsUiMapper {
    fun mapToUi(data: RowsDomainModel): RowsUiModel {
        return RowsUiModel(
            key = data.key,
            label = data.label,
            value = data.value
        )
    }
}