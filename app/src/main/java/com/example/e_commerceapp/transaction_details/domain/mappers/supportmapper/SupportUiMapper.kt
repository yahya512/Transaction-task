package com.example.e_commerceapp.transaction_details.domain.mappers.supportmapper

import com.example.e_commerceapp.transaction_details.domain.model.SupportDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.SupportUiModel

object SupportUiMapper {
    fun mapToUi(data: SupportDomainModel): SupportUiModel {
        return SupportUiModel(
            text = data.text,
            action_label = data.actionLabel
        )
    }
}