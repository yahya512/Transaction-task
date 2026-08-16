package com.example.e_commerceapp.transaction_details.data.model

data class TransactionDetailsDto(
    val id: Int,
    val status_label: String,
    val status_title: String,
    val amount: Double,
    val currency: String,
    val amount_label: String,
    val shareable: Boolean,
    val sections: List<SectionDataModel>,
    val support: SupportDto
)
