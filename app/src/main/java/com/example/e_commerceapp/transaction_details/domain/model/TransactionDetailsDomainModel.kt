package com.example.e_commerceapp.transaction_details.domain.model

data class TransactionDetailsDomainModel(
    val id: Int?,
    val status_label: String?,
    val amount_label: String?,
    val status_title: String?,
    val shareable: Boolean,
    val sections: List<SectionDomainModel>?,
    val support: SupportDomainModel?
)
