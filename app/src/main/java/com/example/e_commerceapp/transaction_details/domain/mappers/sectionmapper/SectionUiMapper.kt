package com.example.e_commerceapp.transaction_details.domain.mappers.sectionmapper

import com.example.e_commerceapp.transaction_details.domain.mappers.rowsmapper.RowsListUiMapper
import com.example.e_commerceapp.transaction_details.domain.model.SectionDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.SectionType
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel

object SectionUiMapper {

    fun mapToUi(sectionItem: SectionDomainModel): SectionUiModel {

        val resultType = when (sectionItem.key) {
            SectionType.TRANSACTION.type -> SectionType.TRANSACTION
            SectionType.PAYMENT.type -> SectionType.PAYMENT
            else -> {
                SectionType.TRANSACTION
            }
        }


        return SectionUiModel(
            key = resultType,
            title = sectionItem.title,
            rows = RowsListUiMapper.mapListToUi(sectionItem.rows)
        )
    }
}