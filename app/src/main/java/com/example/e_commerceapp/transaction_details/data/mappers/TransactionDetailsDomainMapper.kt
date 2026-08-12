package com.example.e_commerceapp.transaction_details.data.mappers

import com.example.e_commerceapp.transaction_details.data.mappers.sectionmapper.SectionListDomainMapper
import com.example.e_commerceapp.transaction_details.data.mappers.supportmapper.SupportDomainMapper
import com.example.e_commerceapp.transaction_details.data.model.TransactionDetailsDto
import com.example.e_commerceapp.transaction_details.domain.model.TransactionDetailsDomainModel

object TransactionDetailsDomainMapper {
    fun mapToDomain(data: TransactionDetailsDto?): TransactionDetailsDomainModel {
        return TransactionDetailsDomainModel(
            id = data?.id,
            status_label = data?.statusLabel,
            status_title = data?.statusTitle,
            amount_label = data?.amountLabel,
            shareable = data?.shareable ?: false,
            support = SupportDomainMapper.mapToDomain(data?.support),
            sections = SectionListDomainMapper.mapListToDomain(data?.sections)
        )
    }
}