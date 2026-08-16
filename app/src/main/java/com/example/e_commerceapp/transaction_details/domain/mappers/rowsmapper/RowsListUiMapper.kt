package com.example.e_commerceapp.transaction_details.domain.mappers.rowsmapper

import com.example.e_commerceapp.transaction_details.domain.model.RowsDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.RowsUiModel

object RowsListUiMapper {
    fun mapListToUi(listRows: List<RowsDomainModel>): List<RowsUiModel> {
        return listRows.map {
            RowsUiMapper.mapToUi(it)
        }
    }
}