package com.example.e_commerceapp.transaction_list.domain.mapper

import com.example.e_commerceapp.transaction_list.domain.model.PaginationDomainModel
import com.example.e_commerceapp.transaction_list.presentation.model.PaginationUiModel

object PaginationUiMapper {
    fun mapToUi(paginate: PaginationDomainModel?): PaginationUiModel {
        return PaginationUiModel(
            currentPage = paginate?.currentPage,
            perPage = paginate?.perPage,
            hasMore = paginate?.hasMore
        )
    }
}