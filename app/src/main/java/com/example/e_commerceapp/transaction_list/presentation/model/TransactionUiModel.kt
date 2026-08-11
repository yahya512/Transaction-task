package com.example.e_commerceapp.transaction_list.presentation.model

data class TransactionUiModel(
    val id: Int?,
    val reference: String?,
    val title: String?,
    val icon: String?,
    val amount_label: String?,
    val status_label: String?,
    val data_label: String?,
    val type: ViewHolderType
)