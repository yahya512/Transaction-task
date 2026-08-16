package com.example.e_commerceapp.transaction_details.presentation.model

sealed class UiDetailsState {
    data class Success(val data: TransactionDetailsUiModel) : UiDetailsState()
    data object Loading : UiDetailsState()
    data class Error(val errorMessage: String) : UiDetailsState()
}