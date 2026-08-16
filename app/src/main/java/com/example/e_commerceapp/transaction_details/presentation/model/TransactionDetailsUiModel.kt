package com.example.e_commerceapp.transaction_details.presentation.model

data class TransactionDetailsUiModel(
    val id: Int?,
    val statusLabel: String?,
    val statusTitle: String?,
    val amountLabel: String?,
    val shareable: Boolean,
    val sections: List<SectionUiModel>?,
    val support: SupportUiModel?
)