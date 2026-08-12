package com.example.e_commerceapp.transaction_list.data.mapper

import com.example.e_commerceapp.transaction_list.data.model.PaginationDto
import com.example.e_commerceapp.transaction_list.domain.model.PaginationDomainModel

object PaginationDomainMapper {
    fun mapToDomain(paginate: PaginationDto?): PaginationDomainModel {
        return PaginationDomainModel(
            currentPage = paginate?.currentPage,
            perPage = paginate?.perPage,
            hasMore = paginate?.hasMore
        )
    }
}