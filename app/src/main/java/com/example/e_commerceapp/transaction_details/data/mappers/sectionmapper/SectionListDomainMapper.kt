package com.example.e_commerceapp.transaction_details.data.mappers.sectionmapper

import com.example.e_commerceapp.transaction_details.data.model.SectionDataModel
import com.example.e_commerceapp.transaction_details.domain.model.SectionDomainModel

object SectionListDomainMapper {
    fun mapListToDomain(data: List<SectionDataModel>?): List<SectionDomainModel>? {
        return data?.let {
            data.map {
                SectionDomainMapper.mapToDomain(it)
            }
        }
    }
}