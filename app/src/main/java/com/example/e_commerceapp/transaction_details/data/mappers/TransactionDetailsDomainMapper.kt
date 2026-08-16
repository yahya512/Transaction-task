package com.example.e_commerceapp.transaction_details.data.mappers

import com.example.e_commerceapp.transaction_details.data.mappers.sectionmapper.SectionListDomainMapper
import com.example.e_commerceapp.transaction_details.data.mappers.supportmapper.SupportDomainMapper
import com.example.e_commerceapp.transaction_details.data.model.TransactionDetailsDto
import com.example.e_commerceapp.transaction_details.domain.model.TransactionDetailsDomainModel

object TransactionDetailsDomainMapper {
    fun mapToDomain(data: TransactionDetailsDto): TransactionDetailsDomainModel {
        return TransactionDetailsDomainModel(
            id = data.id,
            statusLabel = data.statusLabel,
            statusTitle = data.statusTitle,
            amountLabel = data.amountLabel,
            shareable = data.shareable,
            support = SupportDomainMapper.mapToDomain(data.support),
            sections = SectionListDomainMapper.mapListToDomain(data.sections)
        )
    }
}