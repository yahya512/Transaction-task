package com.example.e_commerceapp.transaction_details.data.model

import com.google.gson.annotations.SerializedName

data class SupportDto(
    @SerializedName("text") val text: String,
    @SerializedName("action_label") val actionLabel: String
)
