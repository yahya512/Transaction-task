package com.example.e_commerceapp.transaction_list.domain.model

data class TransactionDomainModel(
    val id: Int?,
    val reference: String?,
    val title: String?,
    val icon: String?,
    val amountLabel: String?,
    val statusLabel: String?,
    val dataLabel: String?,
    val type: String?
)