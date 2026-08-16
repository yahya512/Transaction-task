package com.example.e_commerceapp.transaction_details.presentation.model

data class SectionUiModel(
    val key: SectionType,
    val title: String?,
    val rows: List<RowsUiModel>?
)
