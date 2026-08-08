package com.example.e_commerceapp.transaction_list.presentation.models

sealed class UiState {
    data class Success(val transactions: List<TransactionUiModule>?) : UiState()
    data object Loading : UiState()
    data class Error(val errorMessage: String) : UiState()
}