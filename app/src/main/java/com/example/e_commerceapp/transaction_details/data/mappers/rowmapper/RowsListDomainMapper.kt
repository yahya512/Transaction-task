package com.example.e_commerceapp.transaction_details.data.mappers.rowmapper

import com.example.e_commerceapp.transaction_details.data.model.RowsDataModel
import com.example.e_commerceapp.transaction_details.domain.model.RowsDomainModel

object RowsListDomainMapper {
    fun mapListToDomain(data: List<RowsDataModel>?): List<RowsDomainModel>? {
        return data?.let {
            data.map {
                RowsDomainMapper.mapToDomain(it)
            }
        }
    }
}