package com.example.e_commerceapp.transaction_list.presentation.model

data class TransactionUiModel(
    val id: Int,
    val reference: String,
    val title: String,
    val icon: String,
    val amountLabel: String,
    val status: String,
    val statusLabel: String,
    val dataLabel: String,
    val type: ViewHolderType
)