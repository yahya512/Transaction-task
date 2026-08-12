package com.example.e_commerceapp.transaction_list.domain.model

data class PaginationDomainModel(
    val currentPage: Int?,
    val perPage: Int?,
    val hasMore: Boolean?
)
