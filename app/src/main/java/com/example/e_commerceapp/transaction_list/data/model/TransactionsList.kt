package com.example.e_commerceapp.transaction_list.data.model

data class TransactionsList(
    val transactions: List<TransactionDto>?,
    val pagination: PaginationDto
)
