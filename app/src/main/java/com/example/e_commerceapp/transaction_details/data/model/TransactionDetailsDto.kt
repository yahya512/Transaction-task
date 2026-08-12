package com.example.e_commerceapp.transaction_details.data.model

import com.google.gson.annotations.SerializedName

data class TransactionDetailsDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("status_label") val statusLabel: String?,
    @SerializedName("status_title") val statusTitle: String?,
    @SerializedName("amount") val amount: Double?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("amount_label") val amountLabel: String?,
    @SerializedName("shareable") val shareable: Boolean,
    @SerializedName("sections") val sections: List<SectionDataModel>?,
    @SerializedName("support") val support: SupportDataModel?
)
