package com.example.e_commerceapp.transaction_details.domain.mappers

import com.example.e_commerceapp.transaction_details.domain.mappers.sectionmapper.SectionListUiMapper
import com.example.e_commerceapp.transaction_details.domain.mappers.supportmapper.SupportUiMapper
import com.example.e_commerceapp.transaction_details.domain.model.TransactionDetailsDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.TransactionDetailsUiModel

object TransactionDetailsUiMapper {
    fun mapToUi(data: TransactionDetailsDomainModel?): TransactionDetailsUiModel {
        return TransactionDetailsUiModel(
            id = data?.id,
            status_label = data?.status_label,
            amount_label = data?.amount_label,
            status_title = data?.status_title,
            shareable = data?.shareable ?: false,
            support = SupportUiMapper.mapToUi(data?.support),
            sections = SectionListUiMapper.mapListToUi(data?.sections)
        )
    }
}