package com.example.e_commerceapp.transaction_details.data.mappers.supportmapper

import com.example.e_commerceapp.transaction_details.data.model.SupportDto
import com.example.e_commerceapp.transaction_details.domain.model.SupportDomainModel

object SupportDomainMapper {
    fun mapToDomain(data: SupportDto): SupportDomainModel {
        return SupportDomainModel(
            text = data.text ?: "",
            actionLabel = data.actionLabel ?: ""
        )
    }
}
