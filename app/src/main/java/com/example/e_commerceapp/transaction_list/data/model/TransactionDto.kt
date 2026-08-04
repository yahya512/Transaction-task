package com.example.e_commerceapp.transaction_list.data.model

data class TransactionDto(
    val id: Int?,
    val fees: Int?,
    val reference: String?,
    val receipt_number: String?,
    val service: String?,
    val title: String?,
    val icon: String?,
    val amount: String?,
    val currency: String?,
    val amount_label: String?,
    val status: String?,
    val status_label: String?,
    val payment_method: String?,
    val payment_method_label: String?,
    val date: String?,
    val date_label: String?,
    val datetime_label: String?,
    val created_at: String?,
)