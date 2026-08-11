package com.example.e_commerceapp.transaction_list.presentation.model

sealed class UiState {
    data class Success(val transactions: List<TransactionUiModel>?) : UiState()
    object Loading : UiState()
    data class Error(val errorMessage: String) : UiState()

}