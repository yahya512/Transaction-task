package com.example.e_commerceapp.transaction_details.domain.mappers.sectionmapper

import com.example.e_commerceapp.transaction_details.domain.mappers.rowsmapper.RowsListUiMapper
import com.example.e_commerceapp.transaction_details.domain.model.SectionDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel

object SectionUiMapper {

    fun mapToUi(sectionItem: SectionDomainModel): SectionUiModel {
        return SectionUiModel(
            key = sectionItem.key,
            title = sectionItem.title,
            rows = RowsListUiMapper.mapListToUi(sectionItem.rows)
        )
    }
}