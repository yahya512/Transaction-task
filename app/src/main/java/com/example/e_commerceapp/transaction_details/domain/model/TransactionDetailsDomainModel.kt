package com.example.e_commerceapp.transaction_details.domain.model

data class TransactionDetailsDomainModel(
    val id: Int?,
    val statusLabel: String?,
    val amountLabel: String?,
    val statusTitle: String?,
    val shareable: Boolean,
    val sections: List<SectionDomainModel>?,
    val support: SupportDomainModel?
)
