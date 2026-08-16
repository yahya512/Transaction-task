package com.example.e_commerceapp.transaction_list.presentation.model

data class PaginationUiModel(
    val currentPage: Int?,
    val perPage: Int?,
    val hasMore: Boolean?
)