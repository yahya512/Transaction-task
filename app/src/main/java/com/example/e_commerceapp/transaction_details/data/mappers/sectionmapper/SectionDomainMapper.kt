package com.example.e_commerceapp.transaction_details.data.mappers.sectionmapper

import com.example.e_commerceapp.transaction_details.data.mappers.rowmapper.RowsListDomainMapper
import com.example.e_commerceapp.transaction_details.data.model.SectionDataModel
import com.example.e_commerceapp.transaction_details.domain.model.SectionDomainModel

object SectionDomainMapper {
    fun mapToDomain(data: SectionDataModel): SectionDomainModel {
        return SectionDomainModel(
            key = data.key,
            title = data.title,
            rows = RowsListDomainMapper.mapListToDomain(data.rows)
        )
    }
}