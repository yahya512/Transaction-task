package com.example.e_commerceapp.transaction_list.domain.model

data class BaseDomainModel (
    val transactions: List<TransactionDomainModel>?,
    val pagination: PaginationDomainModel
)