package com.example.e_commerceapp.transaction_details.presentation.model

data class TransactionDetailsUiModel(
    val id: Int?,

    val status_label: String?,
    val status_title: String?,
    val amount_label: String?,
    val shareable: Boolean,
    val sections: List<SectionUiModel>?,
    val support: SupportUiModel?
)