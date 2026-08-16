package com.example.e_commerceapp.transaction_details.domain.mappers.sectionmapper

import com.example.e_commerceapp.transaction_details.domain.model.SectionDomainModel
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel

object SectionListUiMapper {
    fun mapListToUi(sectionList: List<SectionDomainModel>): List<SectionUiModel> {
        return sectionList.map {
            SectionUiMapper.mapToUi(it)
        }
    }
}