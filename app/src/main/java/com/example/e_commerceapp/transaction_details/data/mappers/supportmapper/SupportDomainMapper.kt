package com.example.e_commerceapp.transaction_details.data.mappers.supportmapper

import com.example.e_commerceapp.transaction_details.data.model.SupportDataModel
import com.example.e_commerceapp.transaction_details.domain.model.SupportDomainModel

object SupportDomainMapper {
    fun mapToDomain(data: SupportDataModel): SupportDomainModel {
        return SupportDomainModel(
            text = data.text,
            actionLabel = data.actionLabel
        )
    }
}
