package com.example.e_commerceapp.transaction_details.data.mappers.rowmapper

import com.example.e_commerceapp.transaction_details.data.model.RowsDataModel
import com.example.e_commerceapp.transaction_details.domain.model.RowsDomainModel

object RowsDomainMapper {
    fun mapToDomain(data: RowsDataModel?): RowsDomainModel {
        return RowsDomainModel(
            key = data?.key,
            label = data?.label,
            value = data?.value
        )
    }
}