package com.example.e_commerceapp.transaction_list.data.model

import com.google.gson.annotations.SerializedName

data class TransactionDto(
    @SerializedName("id") val id: Int,
    @SerializedName("fees") val fees: Double,
    @SerializedName("amount") val amount: Double,
    @SerializedName("reference") val reference: String,
    @SerializedName("receipt_number") val receiptNumber: String,
    @SerializedName("service") val service: String,
    @SerializedName("title") val title: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("amount_label") val amountLabel: String,
    @SerializedName("status") val status: String,
    @SerializedName("status_label") val statusLabel: String,
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("payment_method_label") val paymentMethodLabel: String,
    @SerializedName("date") val date: String,
    @SerializedName("date_label") val dateLabel: String,
    @SerializedName("datetime_label") val datetimeLabel: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("type") val type: String
)